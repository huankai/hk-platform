package com.hk.pms.service;

import java.util.Collection;
import java.util.List;

import com.hk.commons.util.ByteConstants;
import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysResource;

/**
 * @author kevin
 * @date 2018-08-28 16:40
 */
public interface SysResourceService extends JdbcBaseService<SysResource, String> {

    List<ResourceTree> findByPermissionIds(String appId,Collection<String> permissions);

    default void disable(String id) {
        findById(id).ifPresent(item -> {
            item.setState(ByteConstants.ZERO);
            updateById(item);
        });
    }

    default void enable(String id) {
        findById(id).ifPresent(item -> {
            item.setState(ByteConstants.ONE);
            updateById(item);
        });
    }
}
