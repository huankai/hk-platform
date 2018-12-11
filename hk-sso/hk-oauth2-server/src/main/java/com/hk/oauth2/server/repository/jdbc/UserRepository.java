package com.hk.oauth2.server.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.repository.jdbc.custom.CustomUserRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-07-25 08:49
 */
public interface UserRepository extends StringIdJdbcRepository<SysUser> ,CustomUserRepository {

    /**
     * @param account 登陆名
     * @return SysUser
     */
    @Query(value = "select u.* from sys_user u where account = :account")
    Optional<SysUser> findByAccount(@Param("account") String account);

    /**
     * @param phone 手机号
     * @return SysUser
     */
    @Query(value = "select u.* from sys_user u where phone = :phone")
    Optional<SysUser> findByPhone(@Param("phone") String phone);

    /**
     * @param email 邮箱号
     * @return SysUser
     */
    @Query(value = "select u.* from sys_user u where email = :email")
    Optional<SysUser> findByEmail(@Param("email") String email);
}
