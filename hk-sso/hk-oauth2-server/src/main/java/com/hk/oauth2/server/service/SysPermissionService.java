package com.hk.oauth2.server.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.oauth2.server.entity.SysPermission;

import java.util.List;
import java.util.Set;

/**
 * @author: kevin
 * @date: 2018-08-03 08:58
 */
public interface SysPermissionService extends JdbcBaseService<SysPermission, String> {

    List<SysPermission> findByAppIdAndRoleIds(String appId, Set<String> roleIds);
}
