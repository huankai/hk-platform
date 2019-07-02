package com.hk.pms.service;


import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysRolePermission;

/**
 * @author kevin
 * @date 2018-04-12 16:56
 */
public interface SysRolePermissionService extends JpaBaseService<SysRolePermission, Long> {

    /**
     * @param roleId       roleId
     * @param permissionId permissionId
     */
    void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);

    void updateRolePermission(Long roleId, Long[] permissionIds);

    void addPermissionRole(Long permissionId, Long[] roleIds);
}
