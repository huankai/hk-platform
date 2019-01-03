package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysDeptRole;

/**
 * @author kevin
 * @date 2018-04-12 16:37
 */
public interface SysDeptRoleRepository extends StringIdJdbcRepository<SysDeptRole> {

    void deleteByDeptIdAndRoleId(String deptId, String roleId);
}
