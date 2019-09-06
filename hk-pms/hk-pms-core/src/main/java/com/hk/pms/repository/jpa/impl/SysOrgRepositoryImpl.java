package com.hk.pms.repository.jpa.impl;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.Contants;
import com.hk.commons.util.StringUtils;
import com.hk.core.jdbc.JdbcDaoSupport;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.platform.commons.tree.AntDesignTreeNode;
import com.hk.pms.repository.jpa.custom.CustomSysOrgRepository;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-09-05 22:13
 */
public class SysOrgRepositoryImpl extends JdbcDaoSupport implements CustomSysOrgRepository {

    @Override
    public List<AntDesignTreeNode> findRootList(Long currentOrgId) {
        return findList(Contants.DEFAULT_VALUE_LONG, currentOrgId);
    }

    private List<AntDesignTreeNode> findList(Long parentId, Long currentId) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sys_org");
        arguments.setFields(ArrayUtils.asArrayList("id AS value", "org_name AS title", "state AS disabled"));
        arguments.getConditions().addCondition(new SimpleCondition("parent_id", parentId));
        List<AntDesignTreeNode> result = jdbcSession.queryForList(arguments, false, AntDesignTreeNode.class).getResult();
        for (AntDesignTreeNode item : result) {
            item.setDisabled(!item.getDisabled());//取反
            if (null != currentId && StringUtils.equals(item.getValue().toString(), currentId.toString())) {
                item.setDisabled(true);
            }
        }
        return result;
    }

    @Override
    public List<AntDesignTreeNode> findChildList(Long parentId, Long currentId) {
        return findList(parentId, currentId);
    }
}
