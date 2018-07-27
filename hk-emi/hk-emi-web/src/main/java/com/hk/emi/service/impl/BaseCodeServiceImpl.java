package com.hk.emi.service.impl;


import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.repository.BaseCodeRepostory;
import com.hk.emi.service.BaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date 2018年1月24日下午1:46:36
 */
@Service
@CacheConfig(cacheNames = {"BaseCode"})
public class BaseCodeServiceImpl extends BaseServiceImpl<BaseCode, String> implements BaseCodeService {

    private final BaseCodeRepostory baseCodeRepostory;

    @Autowired
    public BaseCodeServiceImpl(BaseCodeRepostory baseCodeRepostory) {
        this.baseCodeRepostory = baseCodeRepostory;
    }

    @Override
    protected BaseRepository<BaseCode, String> getBaseRepository() {
        return baseCodeRepostory;
    }
}
