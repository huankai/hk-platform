package com.hk.emi.service.impl;

import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.repository.jpa.BaseCodeRepository;
import com.hk.emi.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author kevin
 * @date 2018年1月24日下午1:46:36
 */
@Service
@CacheConfig(cacheNames = {"BaseCode"})
public class BaseCodeServiceImpl extends EnableJpaCacheServiceImpl<BaseCode, Long> implements BaseCodeService {

    private final BaseCodeRepository baseCodeRepository;

    @Autowired
    public BaseCodeServiceImpl(BaseCodeRepository baseCodeRepository) {
        this.baseCodeRepository = baseCodeRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("baseCode",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("codeName",ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    protected BaseJpaRepository<BaseCode, Long> getBaseRepository() {
        return baseCodeRepository;
    }

    public Optional<BaseCode> findByBaseCode(String baseCode) {
        return baseCodeRepository.findByBaseCode(baseCode);
    }

    @Override
    public BaseCode insert(BaseCode t) {
        return insert(t, item -> {
            if (Objects.isNull(item.getIsGb())) {
                item.setIsGb(Boolean.FALSE);
            }
            return item;
        });
    }

}
