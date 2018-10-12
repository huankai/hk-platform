package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.pms.domain.SysDeptRole;

/**
 * @author: kevin
 * @date: 2018-04-12 16:37
 */
public interface SysDeptRoleRepository extends StringIdJpaRepository<SysDeptRole> {

    void deleteByDeptIdAndRoleId(String deptId, String roleId);
}
