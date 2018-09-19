package com.hk.sso.server.repository.impl;

import com.hk.core.data.commons.query.Operator;
import com.hk.core.data.jdbc.JdbcDaoSupport;
import com.hk.core.data.jdbc.SelectArguments;
import com.hk.core.data.jdbc.query.CompositeCondition;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.sso.server.entity.SysPermission;
import com.hk.sso.server.repository.custom.CustomSysPermissionRepository;

import java.util.List;
import java.util.Set;

/**
 * @author: kevin
 * @date: 2018-09-19 10:29
 */
public class SysPermissionRepositoryImpl extends JdbcDaoSupport implements CustomSysPermissionRepository {

    @Override
    public List<SysPermission> findByAppIdAndRoleIds(String appId, Set<String> roleIdSet) {
        SelectArguments arguments = new SelectArguments();
        arguments.fields("t1.*");
        arguments.setFrom("sys_permission t1 JOIN sys_role_permission t2 ON t1.id = t2.permission_id");
        CompositeCondition conditions = arguments.getConditions();
        conditions.addCondition(new SimpleCondition("t1.app_id", appId));
        conditions.addCondition(new SimpleCondition("t2.role_id", Operator.IN, roleIdSet));
        return jdbcSession.queryForList(arguments, false, SysPermission.class).getResult();
    }
}
