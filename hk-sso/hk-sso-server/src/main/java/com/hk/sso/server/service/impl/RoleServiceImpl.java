package com.hk.sso.server.service.impl;

import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.sso.server.entity.SysRole;
import com.hk.sso.server.mappers.SysRoleMapper;
import com.hk.sso.server.repository.RoleRepository;
import com.hk.sso.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-03 08:53
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole, String> implements RoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected BaseRepository<SysRole, String> getBaseRepository() {
        return roleRepository;
    }

    @Override
    public List<SysRole> findRoleByAppIdAndUserId(String appId, String userId) {
        return roleMapper.findRoleByAppIdAndUserId(appId, userId);
    }
}
