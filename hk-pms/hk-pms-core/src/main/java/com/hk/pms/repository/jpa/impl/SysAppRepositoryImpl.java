package com.hk.pms.repository.jpa.impl;

import com.hk.commons.util.ArrayUtils;
import com.hk.core.jdbc.JdbcDaoSupport;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.core.query.Order;
import com.hk.platform.commons.ui.SelectOption;
import com.hk.pms.repository.jpa.custom.CustomSysAppRepository;

import java.util.List;

/**
 * @author kevin
 * @date 2019-9-10 15:52
 */
public class SysAppRepositoryImpl extends JdbcDaoSupport implements CustomSysAppRepository {

    @Override
    public List<SelectOption> getSelectOptionList() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sys_oauth_client_details");
        arguments.setFields(ArrayUtils.asArrayList("id", "app_name AS name"));
        arguments.getConditions().addConditions(new SimpleCondition("app_status", 1));
        arguments.setOrders(ArrayUtils.asArrayList(Order.asc("app_code")));
        return jdbcSession.queryForList(arguments, false, SelectOption.class).getResult();
    }
}
