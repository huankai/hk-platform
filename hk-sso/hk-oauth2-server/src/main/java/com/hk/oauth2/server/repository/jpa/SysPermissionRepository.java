package com.hk.oauth2.server.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.oauth2.server.entity.SysPermission;
import com.hk.oauth2.server.repository.jpa.custom.CustomSysPermissionRepository;

/**
 * @author kevin
 * @date 2018-08-03 09:01
 */
public interface SysPermissionRepository extends LongIdJpaRepository<SysPermission>, CustomSysPermissionRepository {


}
