package com.hk.oauth2.server.service.impl;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.oauth2.server.entity.SysRole;
import com.hk.oauth2.server.mappers.SysRoleMapper;
import com.hk.oauth2.server.repository.jpa.RoleRepository;
import com.hk.oauth2.server.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-03 08:53
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends JpaServiceImpl<SysRole, Long> implements RoleService {

    private final SysRoleMapper roleMapper;

    private final RoleRepository roleRepository;


    @Override
    protected BaseJpaRepository<SysRole, Long> getBaseRepository() {
        return roleRepository;
    }

    @Override
    public List<SysRole> findRoleByAppIdAndUserId(Long appId, Long userId) {
        return roleMapper.findRoleByAppIdAndUserId(appId, userId);
    }
}
