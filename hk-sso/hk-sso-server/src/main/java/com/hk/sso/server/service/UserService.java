package com.hk.sso.server.service;

import com.hk.core.service.BaseService;
import com.hk.sso.server.entity.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-07-31 12:52
 */
public interface UserService extends BaseService<SysUser, String> {

    /**
     * 登陆名查询
     *
     * @param loginName 可以是手机号或者邮箱号
     * @return
     */
    Optional<SysUser> findByLoginName(String loginName);

    void resetPassword(String userId, String newPass);
}
