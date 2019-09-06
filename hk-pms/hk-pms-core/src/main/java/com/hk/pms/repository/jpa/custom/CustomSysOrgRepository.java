package com.hk.pms.repository.jpa.custom;

import com.hk.platform.commons.tree.AntDesignTreeNode;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-09-05 22:13
 */
public interface CustomSysOrgRepository {

    List<AntDesignTreeNode> findRootList(Long currentOrgId);

    List<AntDesignTreeNode> findChildList(Long parentId, Long currentId);
}
