package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysDeptRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author kevin
 * @date 2018-04-12 16:37
 */
public interface SysDeptRoleRepository extends LongIdJpaRepository<SysDeptRole> {

    //    @Query(value = "delete from sys_dept_role where dept_id = :deptId and role_id = :roleId")
//    void deleteByDeptIdAndRoleId(@Param(value = "deptId") String deptId, @Param(value = "roleId") String roleId);
//    @Query(name = "DELETE FROM SysDeptRole t WHERE t.deptId = ?1 AND t.roleId = ?2")
    void deleteByDeptIdAndRoleId(Long deptId, Long roleId);

//    @Query(value = "DELETE FROM sys_dept_role WHERE dept_id = ?1")
    void deleteByDeptId(Long deptId);

//    @Query(value = "SELECT t FROM sys_dept_role t WHERE t.role_id = ?1")
    List<SysDeptRole> findByRoleId(Long roleId);
}
