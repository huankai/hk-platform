package com.hk.sso.server.service.impl;

import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.service.impl.BaseServiceImpl;
import com.hk.sso.server.entity.SysPermission;
import com.hk.sso.server.repository.SysPermissionRepository;
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
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermission, String> implements SysPermissionService {

    @Autowired
    private SysPermissionRepository permissionRepository;

    @Override
    public List<SysPermission> findByAppIdAndRoleIds(String appId, Set<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {//必须不能为空
            return Collections.emptyList();
        }
        return permissionRepository.findByAppIdAndRoleIds(appId, roleIds);
    }

    @Override
    protected BaseRepository<SysPermission, String> getBaseRepository() {
        return permissionRepository;
    }
}
