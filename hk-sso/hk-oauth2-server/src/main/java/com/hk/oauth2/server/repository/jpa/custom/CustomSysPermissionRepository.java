package com.hk.oauth2.server.repository.jpa.custom;

import com.hk.oauth2.server.entity.SysPermission;

import java.util.List;
import java.util.Set;

/**
 * @author kevin
 * @date 2018-08-24 13:56
 */
public interface CustomSysPermissionRepository {

    List<SysPermission> findByAppIdAndRoleIds(Long appId, Set<Long> roleIdSet);


}
