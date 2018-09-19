package com.hk.sso.server.repository.custom;

import com.hk.sso.server.entity.SysPermission;

import java.util.List;
import java.util.Set;

/**
 * @author: kevin
 * @date: 2018-08-24 13:56
 */
public interface CustomSysPermissionRepository {

    List<SysPermission> findByAppIdAndRoleIds(String appId, Set<String> roleIdSet);


}
