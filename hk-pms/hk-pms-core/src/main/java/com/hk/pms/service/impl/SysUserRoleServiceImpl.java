package com.hk.pms.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.pms.domain.SysUserRole;
import com.hk.pms.repository.jdbc.SysUserRoleRepository;
import com.hk.pms.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-04-12 17:02
 */
@Service
public class SysUserRoleServiceImpl extends JdbcServiceImpl<SysUserRole, String> implements SysUserRoleService {

    private final SysUserRoleRepository sysUserRoleRepository;

    @Autowired
    public SysUserRoleServiceImpl(SysUserRoleRepository sysUserRoleRepository) {
        this.sysUserRoleRepository = sysUserRoleRepository;
    }

    /**
     * 返回 BaseRepository
     *
     * @return
     */
    @Override
    protected JdbcRepository<SysUserRole, String> getBaseRepository() {
        return sysUserRoleRepository;
    }

    @Override
    public void deleteByUserIdAndRoleId(String userId, String roleId) {
        sysUserRoleRepository.deleteByUserIdAndRoleId(userId, roleId);
    }

    /**
     * 先删除，再添加
     *
     * @param userId  userId
     * @param roleIds roleIds
     */
    @Override
    public void updateUserRole(String userId, String[] roleIds) {
        AssertUtils.notEmptyWithI18n(userId, "userId");
        deleteByUserId(userId);
        if (ArrayUtils.isNotEmpty(roleIds)) {
            List<SysUserRole> addList = new ArrayList<>();
            for (String roleId : roleIds) {
                addList.add(SysUserRole.builder().userId(userId).roleId(roleId).build());
            }
            batchInsert(addList);
        }
    }

    /**
     * 角色添加用户，存在的用户不会添加
     *
     * @param roleId  roleId
     * @param userIds userIds
     */
    @Override
    public void addRoleUser(String roleId, String[] userIds) {
        AssertUtils.notEmptyWithI18n(roleId, "roleId");
        if (ArrayUtils.isNotEmpty(userIds)) {
            List<SysUserRole> userRoleList = findByRoleId(roleId);
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<SysUserRole> addList = new ArrayList<>();
                List<String> userIdList = userRoleList.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
                for (String userId : userIds) {
                    if (!userIdList.contains(userId)) {
                        addList.add(SysUserRole.builder().userId(userId).roleId(roleId).build());
                    }
                }
                batchInsert(addList);
            }
        }
    }

    private void deleteByUserId(String userId) {
        sysUserRoleRepository.deleteByUserId(userId);
    }

    private List<SysUserRole> findByRoleId(String roleId) {
        return sysUserRoleRepository.findByRoleId(roleId);
    }
}
