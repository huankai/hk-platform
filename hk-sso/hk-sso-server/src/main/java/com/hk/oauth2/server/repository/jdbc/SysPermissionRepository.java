package com.hk.sso.server.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.sso.server.entity.SysPermission;
import com.hk.sso.server.repository.jdbc.custom.CustomSysPermissionRepository;

/**
 * @author: kevin
 * @date: 2018-08-03 09:01
 */
public interface SysPermissionRepository extends StringIdJdbcRepository<SysPermission>, CustomSysPermissionRepository {


}
