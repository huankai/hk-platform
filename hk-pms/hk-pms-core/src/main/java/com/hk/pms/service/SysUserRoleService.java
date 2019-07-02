package com.hk.pms.service;


import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysUserRole;

/**
 * @author kevin
 * @date 2018-04-12 17:02
 */
public interface SysUserRoleService extends JpaBaseService<SysUserRole, Long> {

    void deleteByUserIdAndRoleId(Long userId, Long roleId);

    /**
     * 更新用户角色
     *
     * @param userId  userId
     * @param roleIds roleIds
     */
    void updateUserRole(Long userId, Long[] roleIds);

    /**
     * 添加角色用户
     *
     * @param roleId  roleId
     * @param userIds userIds
     */
    void addRoleUser(Long roleId, Long[] userIds);
}
