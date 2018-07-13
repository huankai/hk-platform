package com.hk.pms.service;


import com.hk.core.service.BaseService;
import com.hk.pms.domain.SysRolePermission;

/**
 * @author: kevin
 * @date 2018-04-12 16:56
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission, String> {

    /**
     * @param roleId       roleId
     * @param permissionId permissionId
     */
    void deleteByRoleIdAndPermissionId(String roleId, String permissionId);
}
