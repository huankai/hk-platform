package com.hk.pms.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysUserRole;

/**
 * @author: kevin
 * @date: 2018-04-12 16:42
 */
public interface SysUserRoleRepository extends StringRepository<SysUserRole> {

    void deleteByUserIdAndRoleId(String userId, String roleId);
}
