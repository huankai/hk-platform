package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.oauth2.server.entity.SysPermission;
import com.hk.oauth2.server.repository.jpa.SysPermissionRepository;
import com.hk.oauth2.server.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author kevin
 * @date 2018-08-03 08:59
 */
@Service
public class SysPermissionServiceImpl extends JpaServiceImpl<SysPermission, Long> implements SysPermissionService {

    private final SysPermissionRepository permissionRepository;

    @Autowired
    public SysPermissionServiceImpl(SysPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<SysPermission> findByAppIdAndRoleIds(Long appId, Set<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {//必须不能为空
            return Collections.emptyList();
        }
        return permissionRepository.findByAppIdAndRoleIds(appId, roleIds);
    }

    @Override
    protected BaseJpaRepository<SysPermission, Long> getBaseRepository() {
        return permissionRepository;
    }
}
