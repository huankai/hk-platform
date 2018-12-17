package com.hk.oauth2.server.service.impl;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.oauth2.server.entity.SysOrg;
import com.hk.oauth2.server.repository.jdbc.SysOrgRepository;
import com.hk.oauth2.server.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kevin
 * @date 2018-10-25 15:15
 */
@Service
public class SysOrgServiceImpl extends JdbcServiceImpl<SysOrg, String> implements SysOrgService {

    private SysOrgRepository sysOrgRepository;

    @Autowired
    public SysOrgServiceImpl(SysOrgRepository sysOrgRepository) {
        this.sysOrgRepository = sysOrgRepository;
    }

    @Override
    protected JdbcRepository<SysOrg, String> getBaseRepository() {
        return sysOrgRepository;
    }
}
