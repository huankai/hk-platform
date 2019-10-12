package com.hk.pms.service;


import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysDeptRole;

/**
 * @author kevin
 * @date 2018-04-12 16:48
 */
public interface SysDeptRoleService extends JpaBaseService<SysDeptRole, Long> {

    void deleteByDeptIdAndRoleId(Long deptId, Long roleId);

    void updateDeptRole(Long deptId, Long[] roleIds);

    void addRoleDept(Long roleId, Long[] deptIds);
}
