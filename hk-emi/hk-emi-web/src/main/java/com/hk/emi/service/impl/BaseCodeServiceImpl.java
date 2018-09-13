package com.hk.emi.service.impl;


import com.hk.commons.util.StringUtils;
import com.hk.core.cache.service.EnableCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.exception.ServiceException;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.repository.BaseCodeRepository;
import com.hk.emi.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: kevin
 * @date 2018年1月24日下午1:46:36
 */
@Service
@CacheConfig(cacheNames = {"BaseCode"})
public class BaseCodeServiceImpl extends EnableCacheServiceImpl<BaseCode, String> implements BaseCodeService {

    private final BaseCodeRepository baseCodeRepository;

    @Autowired
    public BaseCodeServiceImpl(BaseCodeRepository baseCodeRepository) {
        this.baseCodeRepository = baseCodeRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("baseCode", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("codeName", ExampleMatcher.GenericPropertyMatcher::contains);
    }

    @Override
    protected BaseRepository<BaseCode, String> getBaseRepository() {
        return baseCodeRepository;
    }

    @Override
    protected BaseCode saveBefore(BaseCode entity) throws ServiceException {
        Optional<BaseCode> optional = findByBaseCode(entity.getBaseCode());
        if (optional.isPresent() && StringUtils.notEquals(optional.get().getId(), entity.getId())) {
            throw new ServiceException("已存在的编码：" + entity.getBaseCode());
        }
        return entity;
    }

    public Optional<BaseCode> findByBaseCode(String baseCode) {
        return baseCodeRepository.findByBaseCode(baseCode);
    }
}
