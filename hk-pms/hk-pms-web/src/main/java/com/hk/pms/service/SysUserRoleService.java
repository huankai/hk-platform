package com.hk.pms.service;


import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.domain.SysUserRole;

/**
 * @author kevin
 * @date 2018-04-12 17:02
 */
public interface SysUserRoleService extends JdbcBaseService<SysUserRole,String> {

    void deleteByUserIdAndRoleId(String userId, String roleId);
}
