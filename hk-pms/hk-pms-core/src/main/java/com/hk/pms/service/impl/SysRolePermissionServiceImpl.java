package com.hk.pms.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysRolePermission;
import com.hk.pms.repository.jpa.SysRolePermissionRepository;
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
public class SysRolePermissionServiceImpl extends JpaServiceImpl<SysRolePermission, Long> implements SysRolePermissionService {

    private final SysRolePermissionRepository sysRolePermissionRepository;

    @Autowired
    public SysRolePermissionServiceImpl(SysRolePermissionRepository sysRolePermissionRepository) {
        this.sysRolePermissionRepository = sysRolePermissionRepository;
    }

    /**
     * 返回 BaseRepository
     */
    @Override
    protected BaseJpaRepository<SysRolePermission, Long> getBaseRepository() {
        return sysRolePermissionRepository;
    }

    @Override
    public void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        sysRolePermissionRepository.deleteByRoleIdAndPermissionId(roleId, permissionId);
    }

    @Override
    public void updateRolePermission(Long roleId, Long[] permissionIds) {
        AssertUtils.notNull(roleId, "roleId");
        deleteByRoleId(roleId);
        if (ArrayUtils.isNotEmpty(permissionIds)) {
            List<SysRolePermission> addList = new ArrayList<>();
            for (Long permissionId : permissionIds) {
                addList.add(SysRolePermission.builder().permissionId(permissionId).roleId(roleId).build());
            }
            batchInsert(addList);
        }
    }

    private void deleteByRoleId(Long roleId) {
        sysRolePermissionRepository.deleteByRoleId(roleId);
    }

    @Override
    public void addPermissionRole(Long permissionId, Long[] roleIds) {
        AssertUtils.notNull(permissionId, "permissionId");
        if (ArrayUtils.isNotEmpty(roleIds)) {
            List<SysRolePermission> userRoleList = findByPermissionId(permissionId);
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<SysRolePermission> addList = new ArrayList<>();
                List<Long> roleIdList = userRoleList.stream().map(SysRolePermission::getRoleId).collect(Collectors.toList());
                for (Long roleId : roleIds) {
                    if (!roleIdList.contains(roleId)) {
                        addList.add(SysRolePermission.builder().permissionId(permissionId).roleId(roleId).build());
                    }
                }
                batchInsert(addList);
            }
        }
    }

    private List<SysRolePermission> findByPermissionId(Long permissionId) {
        return sysRolePermissionRepository.findByPermissionId(permissionId);
    }
}
