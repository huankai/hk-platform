package com.hk.sso.server.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.sso.server.entity.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-07-25 08:49
 */
public interface UserRepository extends StringRepository<SysUser> {

    /**
     * @param account 登陆名
     * @return
     */
    Optional<SysUser> findByAccount(String account);

    /**
     * @param phone
     * @return
     */
    Optional<SysUser> findByPhone(String phone);

    /**
     * @param email
     * @return
     */
    Optional<SysUser> findByEmail(String email);
}
