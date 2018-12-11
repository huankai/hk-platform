package com.hk.oauth2.server.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.oauth2.server.entity.SysRole;

import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-03 08:53
 */
public interface RoleService extends JdbcBaseService<SysRole, String> {

    List<SysRole> findRoleByAppIdAndUserId(String appId, String userId);
}
