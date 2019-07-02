package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysRolePermission;

import java.util.List;

/**
 * @author kevin
 * @date 2018-04-12 16:41
 */
public interface SysRolePermissionRepository extends LongIdJpaRepository<SysRolePermission> {


    void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);

//    @Query(value = "delete from sys_role_permission where role_id = :roleId")
    void deleteByRoleId(Long roleId);

//    @Query(value = "select * from sys_role_permission where permission_id = :permissionId")
    List<SysRolePermission> findByPermissionId(Long permissionId);
}
