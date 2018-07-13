package com.hk.pms.service;


import com.hk.commons.util.AssertUtils;
import com.hk.core.service.BaseService;
import com.hk.pms.domain.SysUser;

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
    SysUser findByLoginUsername(String username);

    /**
     * 判断用户名是否存在
     *
     * @param username 登陆用户名
     */
    default void existsByLoginUsername(String username) {
        AssertUtils.isTrue(findByLoginUsername(username) == null, "username [" + username + "] is exists");
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
}
