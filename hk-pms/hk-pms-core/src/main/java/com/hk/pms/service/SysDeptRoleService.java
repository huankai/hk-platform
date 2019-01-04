package com.hk.pms.service;


import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.domain.SysDeptRole;

/**
 * @author kevin
 * @date 2018-04-12 16:48
 */
public interface SysDeptRoleService extends JdbcBaseService<SysDeptRole, String> {

    void deleteByDeptIdAndRoleId(String deptId, String roleId);

    void updateDeptRole(String deptId, String[] roleIds);

    void addRoleDept(String roleId, String[] deptIds);
}
