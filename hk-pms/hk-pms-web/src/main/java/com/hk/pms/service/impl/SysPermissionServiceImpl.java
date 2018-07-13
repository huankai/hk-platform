package com.hk.pms.service.impl;


import com.hk.core.data.commons.dao.BaseDao;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.pms.domain.SysPermission;
import com.hk.pms.repository.SysPermissionRepository;
import com.hk.pms.service.SysPermissionService;
import com.hk.pms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-04-12 16:53
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String> implements SysPermissionService {

    private final SysPermissionRepository sysPermissionRepository;

    private SysRoleService roleService;

    @Autowired
    public SysPermissionServiceImpl(SysPermissionRepository sysPermissionRepository) {
        this.sysPermissionRepository = sysPermissionRepository;
    }

    @Autowired
    public void setRoleService(SysRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected BaseDao<SysPermission, String> getBaseDao() {
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
