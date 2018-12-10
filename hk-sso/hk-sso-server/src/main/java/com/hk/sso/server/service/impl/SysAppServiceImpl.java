package com.hk.sso.server.service.impl;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.sso.server.entity.SysApp;
import com.hk.sso.server.repository.jdbc.SysAppRepository;
import com.hk.sso.server.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-08-03 08:57
 */
@Service
//@CacheConfig(cacheNames = "SysApp")
public class SysAppServiceImpl extends JdbcServiceImpl<SysApp, String> implements SysAppService {

    private final SysAppRepository appRepository;

    @Autowired
    public SysAppServiceImpl(SysAppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    protected JdbcRepository<SysApp, String> getBaseRepository() {
        return appRepository;
    }
}
