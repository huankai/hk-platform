package com.hk.emi.service.impl;


import com.hk.core.cache.service.EnableJdbcCacheServiceImpl;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.repository.jpa.BaseCodeRepository;
import com.hk.emi.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018年1月24日下午1:46:36
 */
@Service
@CacheConfig(cacheNames = {"BaseCode"})
public class BaseCodeServiceImpl extends EnableJdbcCacheServiceImpl<BaseCode, String> implements BaseCodeService {

    private final BaseCodeRepository baseCodeRepository;

    @Autowired
    public BaseCodeServiceImpl(BaseCodeRepository baseCodeRepository) {
        this.baseCodeRepository = baseCodeRepository;
    }

    @Override
    protected JdbcRepository<BaseCode, String> getBaseRepository() {
        return baseCodeRepository;
    }

    public Optional<BaseCode> findByBaseCode(String baseCode) {
        return baseCodeRepository.findByBaseCode(baseCode);
    }
}
