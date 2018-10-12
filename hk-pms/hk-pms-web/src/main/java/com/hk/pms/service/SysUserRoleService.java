package com.hk.pms.service;


import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysUserRole;

/**
 * @author: kevin
 * @date: 2018-04-12 17:02
 */
public interface SysUserRoleService extends JpaBaseService<SysUserRole,String> {

    void deleteByUserIdAndRoleId(String userId, String roleId);
}
