package com.hk.sso.server.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.sso.server.entity.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-07-25 08:49
 */
public interface UserRepository extends StringRepository<SysUser> {

    /**
     * @param account 登陆名
     * @return SysUser
     */
    Optional<SysUser> findByAccount(String account);

    /**
     * @param phone 手机号
     * @return SysUser
     */
    Optional<SysUser> findByPhone(String phone);

    /**
     * @param email 邮箱号
     * @return SysUser
     */
    Optional<SysUser> findByEmail(String email);
}
