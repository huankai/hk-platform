package com.hk.sso.server.service.impl;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.sso.server.entity.SysRole;
import com.hk.sso.server.mappers.SysRoleMapper;
import com.hk.sso.server.repository.jdbc.RoleRepository;
import com.hk.sso.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-03 08:53
 */
@Service
public class RoleServiceImpl extends JdbcServiceImpl<SysRole, String> implements RoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected JdbcRepository<SysRole, String> getBaseRepository() {
        return roleRepository;
    }

    @Override
    public List<SysRole> findRoleByAppIdAndUserId(String appId, String userId) {
        return roleMapper.findRoleByAppIdAndUserId(appId, userId);
    }
}
