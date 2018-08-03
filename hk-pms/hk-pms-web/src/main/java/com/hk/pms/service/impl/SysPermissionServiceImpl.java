package com.hk.pms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysPermission;
import com.hk.pms.repository.SysPermissionRepository;
import com.hk.pms.service.SysPermissionService;

/**
 * @author: kevin
 * @date 2018-04-12 16:53
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String> implements SysPermissionService {

    private final SysPermissionRepository sysPermissionRepository;

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
//        List<SysRole> roleList = roleService.getRoleList(userId, appId);
//        List<SysPermission> permissions = new ArrayList<>();
////        roleList.forEach(role -> permissions.addAll(findOne(role.get)));
//        return permissions;
        throw new RuntimeException("未实现...");
    }
}
