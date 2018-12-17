package com.hk.pms.service;

import com.hk.commons.util.ByteConstants;
import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.commons.tree.ResourceTree;
import com.hk.pms.domain.SysResource;

import java.util.Collection;
import java.util.List;

/**
 * @author kevin
 * @date 2018-08-28 16:40
 */
public interface SysResourceService extends JdbcBaseService<SysResource, String> {

    List<ResourceTree> findByPermissionIds(Collection<String> permissions);

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
