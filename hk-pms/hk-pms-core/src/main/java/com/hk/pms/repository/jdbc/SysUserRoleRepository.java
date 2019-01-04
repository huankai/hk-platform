package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysUserRole;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author kevin
 * @date 2018-04-12 16:42
 */
public interface SysUserRoleRepository extends StringIdJdbcRepository<SysUserRole> {

    @Query(value = "delete from sys_user_role where user_id = :userId and role_id = :roleId")
    void deleteByUserIdAndRoleId(@Param(value = "userId") String userId, @Param(value = "roleId") String roleId);

    @Query(value = "select * from sys_user_role where role_id = :roleId")
    List<SysUserRole> findByRoleId(@Param(value = "roleId") String roleId);

    @Query(value = "delete from sys_user_role where user_id = :userId")
    void deleteByUserId(@Param(value = "userId") String userId);
}
