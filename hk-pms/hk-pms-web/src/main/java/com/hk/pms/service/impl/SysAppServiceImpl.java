package com.hk.pms.service.impl;


import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.cache.service.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.JpaBaseRepository;
import com.hk.pms.domain.SysApp;
import com.hk.pms.repository.jpa.SysAppRepository;
import com.hk.pms.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-04-12 11:32
 */
@Service
@CacheConfig(cacheNames = {"app_Cache"})
public class SysAppServiceImpl extends EnableJpaCacheServiceImpl<SysApp, String> implements SysAppService {

    private final SysAppRepository sysAppRepository;

    @Autowired
    public SysAppServiceImpl(SysAppRepository sysAppRepository) {
        this.sysAppRepository = sysAppRepository;
    }

    @Override
    protected JpaBaseRepository<SysApp, String> getBaseRepository() {
        return sysAppRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("appStatus", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("localApp", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("appCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("appName", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    /**
     * 根据appCode 查询唯一
     *
     * @param appCode appCode
     * @return
     */
    @Override
    public Optional<SysApp> findByAppCode(String appCode) {
        AssertUtils.notBlank(appCode);
        return sysAppRepository.findByAppCode(appCode);
    }

    @Override
    public void enable(String id) {
        updateStatus(id, ByteConstants.ONE);
    }

    @Override
    public void disable(String id) {
        updateStatus(id, ByteConstants.ZERO);
    }

    private void updateStatus(String appId, Byte status) {
        findById(appId).ifPresent(app -> {
            app.setAppStatus(status);
            getCurrentProxy().updateById(app);
        });
    }
}
