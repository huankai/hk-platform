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

    List<AntDesignTreeNode> findRootList(Long currentOrgId);

    List<AntDesignTreeNode> findChildList(Long parentId, Long currentId);
}
