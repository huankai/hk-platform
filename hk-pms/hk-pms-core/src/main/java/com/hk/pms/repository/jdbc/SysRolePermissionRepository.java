package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysRolePermission;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author kevin
 * @date 2018-04-12 16:41
 */
public interface SysRolePermissionRepository extends StringIdJdbcRepository<SysRolePermission> {


    void deleteByRoleIdAndPermissionId(String roleId, String permissionId);

    @Query(value = "delete from sys_role_permission where role_id = :roleId")
    void deleteByRoleId(@Param(value = "roleId") String roleId);

    @Query(value = "select * from sys_role_permission where permission_id = :permissionId")
    List<SysRolePermission> findByPermissionId(@Param(value = "permissionId") String permissionId);
}
