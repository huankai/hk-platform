package com.hk.oauth2.server.service.impl;

import com.hk.core.cache.service.impl.EnableJdbcCacheServiceImpl;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.oauth2.server.entity.SysOrgDept;
import com.hk.oauth2.server.repository.jdbc.SysOrgDeptRepository;
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
public class SysOrgDeptServiceImpl extends EnableJdbcCacheServiceImpl<SysOrgDept, String> implements SysOrgDeptService {

    private SysOrgDeptRepository orgDeptRepository;

    @Autowired
    public SysOrgDeptServiceImpl(SysOrgDeptRepository orgDeptRepository) {
        this.orgDeptRepository = orgDeptRepository;
    }

    @Override
    protected JdbcRepository<SysOrgDept, String> getBaseRepository() {
        return orgDeptRepository;
    }
}
