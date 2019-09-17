package com.hk.oauth2.server.service.impl;

import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.oauth2.server.entity.SysOrg;
import com.hk.oauth2.server.repository.jpa.SysOrgRepository;
import com.hk.oauth2.server.service.SysOrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-10-25 15:15
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"SysOrg"})
public class SysOrgServiceImpl extends EnableJpaCacheServiceImpl<SysOrg, Long> implements SysOrgService {

    private final SysOrgRepository sysOrgRepository;


    @Override
    protected BaseJpaRepository<SysOrg, Long> getBaseRepository() {
        return sysOrgRepository;
    }
}
