package com.hk.pms.service;


import com.hk.core.service.exception.ServiceException;
import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysUser;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 17:00
 */
public interface SysUserService extends JpaBaseService<SysUser, Long> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return {@link SysUser}
     */
    Optional<SysUser> findByLoginUsername(String username);

    Optional<SysUser> findByAccount(String username);

    Optional<SysUser> findByEmail(String email);

    Optional<SysUser> findByPhone(String phone);

    /**
     * 判断用户名是否存在
     *
     * @param username 登陆用户名
     */
    default boolean existsByLoginUsername(String username) {
        return findByLoginUsername(username).isPresent();
    }

    /**
     * @param username username
     * @throws ServiceException
     */
    default void checkByLoginUsername(String username) throws ServiceException {
        findByLoginUsername(username).orElseThrow(() -> new ServiceException("用户名 [" + username + "] 已存在"));
    }

    /**
     * 禁用用户
     *
     * @param userId userId
     */
    void disable(Long userId);

    /**
     * 启用用户
     *
     * @param userId userId
     */
    void enable(Long userId);

    void resetPassword(Long userId, String newPassword);

    /**
     * 重设新密码
     *
     * @param id          用户id
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void resetPassword(Long id, String oldPassword, String newPassword);

    /**
     * 根据邮箱号修改密码
     *
     * @param id
     * @param emailCode
     * @param newPassword
     */
    default void resetPasswordByEmail(Long id, String emailCode, String newPassword) {
        throw new UnsupportedOperationException("未实现...");
    }

    /**
     * 根据手机号修改密码
     *
     * @param id
     * @param phoneCode
     * @param newPassword
     */
    default void resetPasswordByPhone(Long id, String phoneCode, String newPassword) {
        throw new UnsupportedOperationException("未实现...");
    }

    void markDeleted(Long id);
}
