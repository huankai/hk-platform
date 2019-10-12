package com.hk.oauth2.server.service;

import com.hk.core.service.jpa.JpaBaseService;
import com.hk.oauth2.server.entity.SysUser;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-07-31 12:52
 */
public interface UserService extends JpaBaseService<SysUser, Long> {

    /**
     * 登陆名查询
     *
     * @param loginName 可以是手机号或者邮箱号
     * @return
     */
    Optional<SysUser> findByLoginName(String loginName);

    /**
     * 重置用户密码
     *
     * @param userId  用户id
     * @param newPass 新密码
     */
    void resetPassword(Long userId, String newPass);

    Optional<SysUser> findByPhone(String phone);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    SysUser registerUser(SysUser user);
}
