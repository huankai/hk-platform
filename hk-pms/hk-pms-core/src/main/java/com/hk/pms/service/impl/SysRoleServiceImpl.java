package com.hk.pms.service.impl;


import com.hk.commons.util.ByteConstants;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysRole;
import com.hk.pms.mappers.SysRoleMapper;
import com.hk.pms.repository.jdbc.SysRoleRepository;
import com.hk.pms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-04-12 16:59
 */
@Service
@CacheConfig(cacheNames = {"SYS_ROLE"})
public class SysRoleServiceImpl extends JdbcServiceImpl<SysRole, String> implements SysRoleService {

    private final SysRoleRepository sysRoleRepository;

    private SysRoleMapper roleMapper;

    @Autowired
    public SysRoleServiceImpl(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }

    @Autowired
    public void setRoleMapper(SysRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 返回 BaseRepository
     */
    @Override
    protected JdbcRepository<SysRole, String> getBaseRepository() {
        return sysRoleRepository;
    }

    @Override
    public List<SysRole> getRoleList(String userId, String appId) {
        List<SysRole> roleList = roleMapper.getRoleList(userId, appId);
        List<SysRole> deptRoleList = roleMapper.getUserDeptRoleList(userId, appId);
        Set<String> idSet = roleList.stream().map(SysRole::getId).collect(Collectors.toSet());
        roleList.addAll(deptRoleList.stream().filter(item -> idSet.contains(item.getId())).collect(Collectors.toList()));
        return roleList;
    }

    @Override
    public void disable(String id) {
        SysRole role = getById(id);
        role.setRoleStatus(ByteConstants.ZERO);
        insertOrUpdate(role);
    }

    @Override
    public void enable(String id) {
        SysRole role = getById(id);
        role.setRoleStatus(ByteConstants.ONE);
        insertOrUpdate(role);
    }

}
