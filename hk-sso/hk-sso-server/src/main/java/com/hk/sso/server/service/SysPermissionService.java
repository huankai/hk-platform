package com.hk.sso.server.service;

import com.hk.core.service.BaseService;
import com.hk.sso.server.entity.SysPermission;

import java.util.List;
import java.util.Set;

/**
 * @author: kevin
 * @date 2018-08-03 08:58
 */
public interface SysPermissionService extends BaseService<SysPermission, String> {

    List<SysPermission> findByAppIdAndRoleIds(String appId, Set<String> roleIds);
}
