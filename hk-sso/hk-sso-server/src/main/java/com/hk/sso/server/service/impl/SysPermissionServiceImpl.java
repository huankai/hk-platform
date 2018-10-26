package com.hk.sso.server.service.impl;

import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.sso.server.entity.SysPermission;
import com.hk.sso.server.repository.jdbc.SysPermissionRepository;
import com.hk.sso.server.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author: kevin
 * @date: 2018-08-03 08:59
 */
@Service
public class SysPermissionServiceImpl extends JdbcServiceImpl<SysPermission, String> implements SysPermissionService {

    private final SysPermissionRepository permissionRepository;

    @Autowired
    public SysPermissionServiceImpl(SysPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<SysPermission> findByAppIdAndRoleIds(String appId, Set<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {//必须不能为空
            return Collections.emptyList();
        }
        return permissionRepository.findByAppIdAndRoleIds(appId, roleIds);
    }

    @Override
    protected JdbcRepository<SysPermission, String> getBaseRepository() {
        return permissionRepository;
    }
}
