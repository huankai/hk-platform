package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysUserRole;

import java.util.List;

/**
 * @author kevin
 * @date 2018-04-12 16:42
 */
public interface SysUserRoleRepository extends LongIdJpaRepository<SysUserRole> {

    //    @Query(value = "delete from SysUserRol_user_role where user_id = :userId and role_id = :roleId")
    void deleteByUserIdAndRoleId(Long userId, Long roleId);

    //    @Query(value = "select * from sys_user_role where role_id = :roleId")
    List<SysUserRole> findByRoleId(Long roleId);

    //    @Query(value = "delete from sys_user_role where user_id = :userId")
    void deleteByUserId(Long userId);
}
