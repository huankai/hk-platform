package com.hk.sso.server.service.impl;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.sso.server.entity.SysOrgDept;
import com.hk.sso.server.repository.jdbc.SysOrgDeptRepository;
import com.hk.sso.server.service.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-10-25 15:15
 */
@Service
public class SysOrgDeptServiceImpl extends JdbcServiceImpl<SysOrgDept, String> implements SysOrgDeptService {

    private SysOrgDeptRepository sysOrgDeptRepository;

    @Autowired
    public SysOrgDeptServiceImpl(SysOrgDeptRepository sysOrgDeptRepository) {
        this.sysOrgDeptRepository = sysOrgDeptRepository;
    }

    @Override
    protected JdbcRepository<SysOrgDept, String> getBaseRepository() {
        return sysOrgDeptRepository;
    }
}
