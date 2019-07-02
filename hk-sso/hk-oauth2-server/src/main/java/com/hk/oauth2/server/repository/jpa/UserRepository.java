package com.hk.oauth2.server.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.repository.jdbc.custom.CustomUserRepository;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-07-25 08:49
 */
public interface UserRepository extends LongIdJpaRepository<SysUser>, CustomUserRepository {

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
