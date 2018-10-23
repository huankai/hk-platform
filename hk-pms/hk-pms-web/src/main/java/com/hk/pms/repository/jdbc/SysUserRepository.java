package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-04-12 16:42
 */
public interface SysUserRepository extends StringIdJdbcRepository<SysUser> {

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
