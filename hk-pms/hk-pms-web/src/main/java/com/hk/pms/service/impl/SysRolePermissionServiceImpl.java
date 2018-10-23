package com.hk.pms.service.impl;


import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysRolePermission;
import com.hk.pms.repository.jdbc.SysRolePermissionRepository;
import com.hk.pms.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: kevin
 * @date: 2018-04-12 17:05
 */
@Service
public class SysRolePermissionServiceImpl extends JdbcServiceImpl<SysRolePermission, String> implements SysRolePermissionService {

    private final SysRolePermissionRepository sysRolePermissionRepository;

    @Autowired
    public SysRolePermissionServiceImpl(SysRolePermissionRepository sysRolePermissionRepository) {
        this.sysRolePermissionRepository = sysRolePermissionRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected JdbcRepository<SysRolePermission, String> getBaseRepository() {
        return sysRolePermissionRepository;
    }

    @Override
    public void deleteByRoleIdAndPermissionId(String roleId, String permissionId) {
        sysRolePermissionRepository.deleteByRoleIdAndPermissionId(roleId,permissionId);
    }
}
