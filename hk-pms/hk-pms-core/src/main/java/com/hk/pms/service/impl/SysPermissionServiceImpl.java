package com.hk.pms.service.impl;


import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysPermission;
import com.hk.pms.domain.SysRole;
import com.hk.pms.mappers.SysPermissionMapper;
import com.hk.pms.repository.jpa.SysPermissionRepository;
import com.hk.pms.service.SysPermissionService;
import com.hk.pms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-04-12 16:53
 */
@Service
public class SysPermissionServiceImpl extends JpaServiceImpl<SysPermission, Long> implements SysPermissionService {

    private final SysPermissionRepository sysPermissionRepository;

    private SysPermissionMapper permissionMapper;

    private SysRoleService roleService;

    @Autowired
    public void setPermissionMapper(SysPermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Autowired
    public void setRoleService(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository) {
        this.sysPermissionRepository = sysPermissionRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseJpaRepository<SysPermission, Long> getBaseRepository() {
        return sysPermissionRepository;
    }

    @Override
    public List<SysPermission> getPermissionList(Long userId, Long appId) {
        List<SysRole> roleList = roleService.getRoleList(userId, appId);
        Set<Long> idSet = roleList.stream().map(SysRole::getId).collect(Collectors.toSet());
        return permissionMapper.getPermissionListByRoleIds(idSet);
    }
}
