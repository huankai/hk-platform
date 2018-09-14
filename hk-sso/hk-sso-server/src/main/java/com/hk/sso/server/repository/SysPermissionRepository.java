package com.hk.sso.server.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.sso.server.entity.SysPermission;
import com.hk.sso.server.repository.custom.CustomSysPermissionRepository;

/**
 * @author: kevin
 * @date: 2018-08-03 09:01
 */
public interface SysPermissionRepository extends StringRepository<SysPermission>, CustomSysPermissionRepository {


}
