package com.hk.pms.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-04-12 16:42
 */
public interface SysUserRepository extends StringRepository<SysUser> {

    /**
     * @param account 登陆名
     * @return SysUser
     */
    Optional<SysUser> findByAccount(String account);

    /**
     * @param phone phone
     * @return SysUser
     */
    Optional<SysUser> findByPhone(String phone);

    /**
     * @param email email
     * @return SysUser
     */
    Optional<SysUser> findByEmail(String email);
}
