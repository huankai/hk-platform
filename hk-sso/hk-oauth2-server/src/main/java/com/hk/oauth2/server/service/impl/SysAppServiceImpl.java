package com.hk.oauth2.server.service.impl;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.oauth2.server.entity.SysApp;
import com.hk.oauth2.server.repository.jpa.SysAppRepository;
import com.hk.oauth2.server.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-08-03 08:57
 */
@Service
//@CacheConfig(cacheNames = "SysApp")
public class SysAppServiceImpl extends JpaServiceImpl<SysApp, Long> implements SysAppService {

    private final SysAppRepository appRepository;

    @Autowired
    public SysAppServiceImpl(SysAppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    protected BaseJpaRepository<SysApp, Long> getBaseRepository() {
        return appRepository;
    }

    @Override
    public SysApp getByClientId(String clientId) {
        return appRepository.getByClientId(clientId);
    }
}
