package com.hk.pms.service.impl;


import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.pms.domain.SysApp;
import com.hk.pms.repository.jpa.SysAppRepository;
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
public class SysAppServiceImpl extends EnableJpaCacheServiceImpl<SysApp, Long> implements SysAppService {

    private final SysAppRepository sysAppRepository;

    /*
     * oauth2 client注册
     */
//    @Autowired
//    private ClientRegistrationService clientRegistrationService;

//    @Autowired
//    private OauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public SysAppServiceImpl(SysAppRepository sysAppRepository) {
        this.sysAppRepository = sysAppRepository;
    }

    @Override
    protected BaseJpaRepository<SysApp, Long> getBaseRepository() {
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
    public void enable(Long id) {
        updateStatus(id, ByteConstants.ONE);
    }

    @Override
    public void disable(Long id) {
        updateStatus(id, ByteConstants.ZERO);
    }

    private void updateStatus(Long appId, Byte status) {
        findById(appId).ifPresent(app -> {
            app.setAppStatus(status);
            updateById(app);
        });
    }
}
