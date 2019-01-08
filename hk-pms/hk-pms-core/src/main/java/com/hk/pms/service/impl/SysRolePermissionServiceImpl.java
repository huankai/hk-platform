package com.hk.pms.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysRolePermission;
import com.hk.pms.repository.jdbc.SysRolePermissionRepository;
import com.hk.pms.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-04-12 17:05
 */
@Service
public class SysRolePermissionServiceImpl extends JdbcServiceImpl<SysRolePermission, String> implements SysRolePermissionService {

    private final SysRolePermissionRepository sysRolePermissionRepository;

    @Autowired
    public SysRolePermissionServiceImpl(SysRolePermissionRepository sysRolePermissionRepository) {
        this.sysRolePermissionRepository = sysRolePermissionRepository;
    }

    /**
     * 返回 BaseRepository
     */
    @Override
    protected JdbcRepository<SysRolePermission, String> getBaseRepository() {
        return sysRolePermissionRepository;
    }

    @Override
    public void deleteByRoleIdAndPermissionId(String roleId, String permissionId) {
        sysRolePermissionRepository.deleteByRoleIdAndPermissionId(roleId, permissionId);
    }

    @Override
    public void updateRolePermission(String roleId, String[] permissionIds) {
        AssertUtils.notEmptyWithI18n(roleId, "roleId");
        deleteByRoleId(roleId);
        if (ArrayUtils.isNotEmpty(permissionIds)) {
            List<SysRolePermission> addList = new ArrayList<>();
            for (String permissionId : permissionIds) {
                addList.add(SysRolePermission.builder().permissionId(permissionId).roleId(roleId).build());
            }
            batchInsert(addList);
        }
    }

    private void deleteByRoleId(String roleId) {
        sysRolePermissionRepository.deleteByRoleId(roleId);
    }

    @Override
    public void addPermissionRole(String permissionId, String[] roleIds) {
        AssertUtils.notEmptyWithI18n(permissionId, "permissionId");
        if (ArrayUtils.isNotEmpty(roleIds)) {
            List<SysRolePermission> userRoleList = findByPermissionId(permissionId);
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<SysRolePermission> addList = new ArrayList<>();
                List<String> roleIdList = userRoleList.stream().map(SysRolePermission::getRoleId).collect(Collectors.toList());
                for (String roleId : roleIds) {
                    if (!roleIdList.contains(roleId)) {
                        addList.add(SysRolePermission.builder().permissionId(permissionId).roleId(roleId).build());
                    }
                }
                batchInsert(addList);
            }
        }
    }

    private List<SysRolePermission> findByPermissionId(String permissionId) {
        return sysRolePermissionRepository.findByPermissionId(permissionId);
    }
}
