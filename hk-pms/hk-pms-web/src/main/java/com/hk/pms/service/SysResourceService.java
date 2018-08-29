package com.hk.pms.service;

import com.hk.commons.util.ArrayUtils;
import com.hk.core.service.BaseService;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysResource;

import java.util.Collection;
import java.util.List;

/**
 * @author: kevin
 * @date 2018-08-28 16:40
 */
public interface SysResourceService extends BaseService<SysResource, String> {

    default List<ResourceTree> findByPermissionIds(String... permissionIds) {
        return findByPermissionIds(ArrayUtils.asList(permissionIds));
    }

    List<ResourceTree> findByPermissionIds(Collection<String> permissions);

    void disable(String id);

    void enable(String id);
}
