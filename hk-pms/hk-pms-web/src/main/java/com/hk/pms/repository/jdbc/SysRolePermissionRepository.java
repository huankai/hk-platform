package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysRolePermission;

/**
 * @author: kevin
 * @date: 2018-04-12 16:41
 */
public interface SysRolePermissionRepository extends StringIdJdbcRepository<SysRolePermission> {


    void deleteByRoleIdAndPermissionId(String roleId, String permissionId);
}
