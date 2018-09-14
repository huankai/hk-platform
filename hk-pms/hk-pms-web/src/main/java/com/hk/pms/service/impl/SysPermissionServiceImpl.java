package com.hk.pms.service.impl;


import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysPermission;
import com.hk.pms.domain.SysRole;
import com.hk.pms.mappers.SysPermissionMapper;
import com.hk.pms.repository.SysPermissionRepository;
import com.hk.pms.service.SysPermissionService;
import com.hk.pms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: kevin
 * @date: 2018-04-12 16:53
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String> implements SysPermissionService {

    private final SysPermissionRepository sysPermissionRepository;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Autowired
    private SysRoleService roleService;

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("appId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("permissionCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("permissionName", ExampleMatcher.GenericPropertyMatchers.contains());
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
    protected BaseRepository<SysPermission, String> getBaseRepository() {
        return sysPermissionRepository;
    }

    @Override
    public List<SysPermission> getPermissionList(String userId, String appId) {
        List<SysRole> roleList = roleService.getRoleList(userId, appId);
        Set<String> idSet = roleList.stream().map(SysRole::getId).collect(Collectors.toSet());
        return permissionMapper.getPermissionListByRoleIds(idSet);
    }
}
