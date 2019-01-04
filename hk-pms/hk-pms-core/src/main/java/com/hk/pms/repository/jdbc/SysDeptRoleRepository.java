package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysDeptRole;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author kevin
 * @date 2018-04-12 16:37
 */
public interface SysDeptRoleRepository extends StringIdJdbcRepository<SysDeptRole> {

    @Query(value = "delete from sys_dept_role where dept_id = :deptId and role_id = :roleId")
    void deleteByDeptIdAndRoleId(@Param(value = "deptId") String deptId, @Param(value = "roleId") String roleId);

    @Query(value = "delete from sys_dept_role where dept_id = :deptId")
    void deleteByDeptId(@Param(value = "deptId") String deptId);

    @Query(value = "select * from sys_dept_role where role_id = :roleId")
    List<SysDeptRole> findByRoleId(@Param(value = "roleId") String roleId);
}
