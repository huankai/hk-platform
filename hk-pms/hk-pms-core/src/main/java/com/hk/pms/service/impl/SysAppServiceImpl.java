package com.hk.pms.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.ObjectUtils;
import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.platform.commons.ui.SelectOption;
import com.hk.pms.domain.SysApp;
import com.hk.pms.repository.jpa.SysAppRepository;
import com.hk.pms.service.SysAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author kevin
 * @date 2018-04-12 11:32
 */
@Service
@CacheConfig(cacheNames = {"app_Cache"})
@RequiredArgsConstructor
public class SysAppServiceImpl extends EnableJpaCacheServiceImpl<SysApp, Long> implements SysAppService {

    private final SysAppRepository sysAppRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Set<String> DEFAULT_SCOPE = ArrayUtils.asHashSet("all");

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher().withIgnorePaths("originalSecret", "clientSecret")
                .withMatcher("appCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("appName", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    /*
     * oauth2 client注册
     */
//    @Autowired
//    private ClientRegistrationService clientRegistrationService;

    //    @Autowired
//    private OauthClientDetailsService oauthClientDetailsService;
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
    @CacheEvict(key = "'id'+#id")
    public void enable(Long id) {
        updateStatus(id, true);
    }

    @Override
    public List<SelectOption> getSelectOptionList() {
        return sysAppRepository.getSelectOptionList();
    }

    @Override
    public SysApp insert(SysApp sysApp) {
        return super.insert(sysApp, app -> {
            String secret = IDGenerator.UUID_32.generate();
            app.setAppStatus(ObjectUtils.defaultIfNull(sysApp.getAppStatus(), true));
            app.setOriginalSecret(secret);
            app.setStartDate(LocalDate.now());
            sysApp.setDeleteStatus(false);
            app.setScope(ObjectUtils.defaultIfNull(sysApp.getScope(), DEFAULT_SCOPE));
            app.setClientSecret(passwordEncoder.encode(secret));
            return app;
        });
    }

    @Override
    @CacheEvict(key = "'id'+#id")
    public void disable(Long id) {
        updateStatus(id, false);
    }

    private void updateStatus(Long appId, boolean status) {
        findById(appId).ifPresent(app -> {
            app.setAppStatus(status);
            updateById(app);
        });
    }
}
