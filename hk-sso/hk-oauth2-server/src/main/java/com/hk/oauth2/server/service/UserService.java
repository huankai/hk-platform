package com.hk.oauth2.server.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.oauth2.server.entity.SysUser;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-07-31 12:52
 */
public interface UserService extends JdbcBaseService<SysUser, String> {

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
    void resetPassword(String userId, String newPass);
}
