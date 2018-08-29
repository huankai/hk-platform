package com.hk.pms.service;


import com.hk.core.exception.ServiceException;
import com.hk.core.service.BaseService;
import com.hk.pms.domain.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-04-12 17:00
 */
public interface SysUserService extends BaseService<SysUser, String> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    Optional<SysUser> findByLoginUsername(String username);

    /**
     * 判断用户名是否存在
     *
     * @param username 登陆用户名
     */
    default void existsByLoginUsername(String username) {
        findByLoginUsername(username).orElseThrow(() -> new ServiceException("用户名 [" + username + "] 已存在"));
    }

    /**
     * 禁用用户
     *
     * @param userId
     */
    void disable(String userId);

    /**
     * 启用用户
     *
     * @param userId
     */
    void enable(String userId);

    /**
     * 重设新密码
     *
     * @param id          用户id
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void resetPassword(String id, String oldPassword, String newPassword);
}
