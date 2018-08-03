package com.hk.pms.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-04-12 16:42
 */
public interface SysUserRepository extends StringRepository<SysUser> {

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
