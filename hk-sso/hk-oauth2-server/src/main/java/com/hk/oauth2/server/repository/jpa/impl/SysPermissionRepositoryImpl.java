package com.hk.oauth2.server.repository.jpa.impl;

import com.hk.oauth2.server.entity.SysPermission;
import com.hk.oauth2.server.repository.jpa.custom.CustomSysPermissionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author kevin
 * @date 2018-09-19 10:29
 */
public class SysPermissionRepositoryImpl implements CustomSysPermissionRepository {

    @Override
    public List<SysPermission> findByAppIdAndRoleIds(Long appId, Set<Long> roleIdSet) {
        // todo
        return Collections.emptyList();
//        SelectArguments arguments = new SelectArguments();
//        arguments.fields("t1.*");
//        arguments.setFrom("sys_permission t1 JOIN sys_role_permission t2 ON t1.id = t2.permission_id");
//        CompositeCondition conditions = arguments.getConditions();
//        conditions.addCondition(new SimpleCondition("t1.app_id", appId));
//        conditions.addCondition(new SimpleCondition("t2.role_id", Operator.IN, roleIdSet));
//        return jdbcSession.queryForList(arguments, false, SysPermission.class).getResult();
    }
}
