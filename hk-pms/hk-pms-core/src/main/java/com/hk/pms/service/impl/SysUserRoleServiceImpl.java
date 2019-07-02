package com.hk.pms.service.impl;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.pms.domain.SysUserRole;
import com.hk.pms.repository.jpa.SysUserRoleRepository;
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
public class SysUserRoleServiceImpl extends JpaServiceImpl<SysUserRole, Long> implements SysUserRoleService {

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
    protected BaseJpaRepository<SysUserRole, Long> getBaseRepository() {
        return sysUserRoleRepository;
    }

    @Override
    public void deleteByUserIdAndRoleId(Long userId, Long roleId) {
        sysUserRoleRepository.deleteByUserIdAndRoleId(userId, roleId);
    }

    /**
     * 先删除，再添加
     *
     * @param userId  userId
     * @param roleIds roleIds
     */
    @Override
    public void updateUserRole(Long userId, Long[] roleIds) {
        AssertUtils.notNull(userId, "userId");
        deleteByUserId(userId);
        if (ArrayUtils.isNotEmpty(roleIds)) {
            List<SysUserRole> addList = new ArrayList<>();
            for (Long roleId : roleIds) {
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
    public void addRoleUser(Long roleId, Long[] userIds) {
        AssertUtils.notNull(roleId, "roleId");
        if (ArrayUtils.isNotEmpty(userIds)) {
            List<SysUserRole> userRoleList = findByRoleId(roleId);
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<SysUserRole> addList = new ArrayList<>();
                List<Long> userIdList = userRoleList.stream().map(SysUserRole::getUserId).collect(Collectors.toList());
                for (Long userId : userIds) {
                    if (!userIdList.contains(userId)) {
                        addList.add(SysUserRole.builder().userId(userId).roleId(roleId).build());
                    }
                }
                batchInsert(addList);
            }
        }
    }

    private void deleteByUserId(Long userId) {
        sysUserRoleRepository.deleteByUserId(userId);
    }

    private List<SysUserRole> findByRoleId(Long roleId) {
        return sysUserRoleRepository.findByRoleId(roleId);
    }
}
