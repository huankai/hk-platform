package com.hk.pms.service;


import com.hk.core.service.BaseService;
import com.hk.pms.domain.SysDeptRole;

/**
 * @author: kevin
 * @date 2018-04-12 16:48
 */
public interface SysDeptRoleService extends BaseService<SysDeptRole,String> {

    void deleteByDeptIdAndRoleId(String deptId, String roleId);
}
