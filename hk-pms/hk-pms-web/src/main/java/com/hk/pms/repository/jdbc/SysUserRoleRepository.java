package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysUserRole;

/**
 * @author kevin
 * @date 2018-04-12 16:42
 */
public interface SysUserRoleRepository extends StringIdJdbcRepository<SysUserRole> {

    void deleteByUserIdAndRoleId(String userId, String roleId);
}
