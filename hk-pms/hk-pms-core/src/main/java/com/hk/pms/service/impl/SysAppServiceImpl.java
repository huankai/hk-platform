package com.hk.pms.service.impl;


import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.cache.service.impl.EnableJdbcCacheServiceImpl;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.pms.domain.SysApp;
import com.hk.pms.repository.jdbc.SysAppRepository;
import com.hk.pms.service.OauthClientDetailsService;
import com.hk.pms.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 11:32
 */
@Service
@CacheConfig(cacheNames = {"app_Cache"})
public class SysAppServiceImpl extends EnableJdbcCacheServiceImpl<SysApp, String> implements SysAppService {

    private final SysAppRepository sysAppRepository;

    /*
     * oauth2 client注册
     */
//    @Autowired
//    private ClientRegistrationService clientRegistrationService;

    @Autowired
    private OauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public SysAppServiceImpl(SysAppRepository sysAppRepository) {
        this.sysAppRepository = sysAppRepository;
    }

    @Override
    protected JdbcRepository<SysApp, String> getBaseRepository() {
        return sysAppRepository;
    }

    /**
     * 根据appCode 查询唯一
     *
     * @param appCode appCode
     * @return SysApp
     */
    @Override
    public Optional<SysApp> findByAppCode(String appCode) {
        AssertUtils.notEmptyWithI18n(appCode, "appCode");
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
            updateById(app);
        });
    }
}
