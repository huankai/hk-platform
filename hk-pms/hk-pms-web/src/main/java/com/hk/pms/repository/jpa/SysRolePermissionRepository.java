package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.pms.domain.SysRolePermission;

/**
 * @author: kevin
 * @date: 2018-04-12 16:41
 */
public interface SysRolePermissionRepository extends StringIdJpaRepository<SysRolePermission> {


    void deleteByRoleIdAndPermissionId(String roleId, String permissionId);
}
