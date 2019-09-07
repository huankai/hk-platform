package com.hk.pms.service;


import com.hk.core.service.jpa.JpaBaseService;
import com.hk.platform.commons.tree.AntDesignTreeNode;
import com.hk.pms.domain.SysOrg;

import java.util.List;

/**
 * @author kevin
 * @date 2018-04-12 16:51
 */
public interface SysOrgService extends JpaBaseService<SysOrg, Long> {

    /**
     * 禁用状态可以选择
     *
     */
    List<AntDesignTreeNode> findRootList();

    /**
     * 禁用状态不能选择
     *
     * @param currentOrgId 当前机构 id
     */
    List<AntDesignTreeNode> findRootList(Long currentOrgId);

    /**
     * 查询子级
     *
     * @param parentId  上级 id
     * @param currentId 当前节点id
     */
    List<AntDesignTreeNode> findChildList(Long parentId, Long currentId);
}
