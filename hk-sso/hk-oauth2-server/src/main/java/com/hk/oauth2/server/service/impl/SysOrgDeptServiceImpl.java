package com.hk.oauth2.server.service.impl;

import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.oauth2.server.entity.SysOrgDept;
import com.hk.oauth2.server.repository.jpa.SysOrgDeptRepository;
import com.hk.oauth2.server.service.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-10-25 15:15
 */
@Service
@CacheConfig(cacheNames = {"SysOrgDept"})
public class SysOrgDeptServiceImpl extends EnableJpaCacheServiceImpl<SysOrgDept, Long> implements SysOrgDeptService {

    private SysOrgDeptRepository orgDeptRepository;

    @Autowired
    public SysOrgDeptServiceImpl(SysOrgDeptRepository orgDeptRepository) {
        this.orgDeptRepository = orgDeptRepository;
    }

    @Override
    protected BaseJpaRepository<SysOrgDept, Long> getBaseRepository() {
        return orgDeptRepository;
    }
}
