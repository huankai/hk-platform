package com.hk.pms.service;

import com.hk.commons.util.ByteConstants;
import com.hk.core.service.BaseService;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysResource;

import java.util.Collection;
import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-28 16:40
 */
public interface SysResourceService extends BaseService<SysResource, String> {

    List<ResourceTree> findByPermissionIds(Collection<String> permissions);

    default void disable(String id) {
        findOne(id).ifPresent(item -> {
            item.setState(ByteConstants.ZERO);
            updateById(item);
        });
    }

    default void enable(String id) {
        findOne(id).ifPresent(item -> {
            item.setState(ByteConstants.ONE);
            updateById(item);
        });
    }
}
