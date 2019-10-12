package com.hk.oauth2.server.service;

import com.hk.core.service.jpa.JpaBaseService;
import com.hk.oauth2.server.entity.SysRole;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-03 08:53
 */
public interface RoleService extends JpaBaseService<SysRole, Long> {

    List<SysRole> findRoleByAppIdAndUserId(Long appId, Long userId);
}
