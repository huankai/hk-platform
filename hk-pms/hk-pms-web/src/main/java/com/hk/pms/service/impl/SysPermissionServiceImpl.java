package com.hk.pms.service.impl;


import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysPermission;
import com.hk.pms.domain.SysRole;
import com.hk.pms.mappers.SysPermissionMapper;
import com.hk.pms.repository.jdbc.SysPermissionRepository;
import com.hk.pms.service.SysPermissionService;
import com.hk.pms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: kevin
 * @date: 2018-04-12 16:53
 */
@Service
public class SysPermissionServiceImpl extends JdbcServiceImpl<SysPermission, String> implements SysPermissionService {

    private final SysPermissionRepository sysPermissionRepository;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private SysRoleService roleService;


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
    protected JdbcRepository<SysPermission, String> getBaseRepository() {
        return sysPermissionRepository;
    }

    @Override
    public List<SysPermission> getPermissionList(String userId, String appId) {
        List<SysRole> roleList = roleService.getRoleList(userId, appId);
        Set<String> idSet = roleList.stream().map(SysRole::getId).collect(Collectors.toSet());
        return permissionMapper.getPermissionListByRoleIds(idSet);
    }
}
