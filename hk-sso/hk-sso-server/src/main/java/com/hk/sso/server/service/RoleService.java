package com.hk.sso.server.service;

import com.hk.core.service.BaseService;
import com.hk.sso.server.entity.SysRole;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-08-03 08:53
 */
public interface RoleService extends BaseService<SysRole, String> {

    List<SysRole> findRoleByAppIdAndUserId(String appId, String userId);
}
