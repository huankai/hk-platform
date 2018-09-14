package com.hk.sso.server.service.impl;

import com.hk.core.cache.service.EnableCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.sso.server.entity.SysApp;
import com.hk.sso.server.repository.SysAppRepository;
import com.hk.sso.server.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-08-03 08:57
 */
@Service
@CacheConfig(cacheNames = "SysApp")
public class SysAppServiceImpl extends EnableCacheServiceImpl<SysApp, String> implements SysAppService {

    @Autowired
    private SysAppRepository appRepository;

    @Override
    protected BaseRepository<SysApp, String> getBaseRepository() {
        return appRepository;
    }
}
