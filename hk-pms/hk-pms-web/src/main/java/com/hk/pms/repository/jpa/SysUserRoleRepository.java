package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.pms.domain.SysUserRole;

/**
 * @author: kevin
 * @date: 2018-04-12 16:42
 */
public interface SysUserRoleRepository extends StringIdJpaRepository<SysUserRole> {

    void deleteByUserIdAndRoleId(String userId, String roleId);
}
