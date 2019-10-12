package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysUser;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 16:42
 */
public interface SysUserRepository extends StringIdJdbcRepository<SysUser> {

    /**
     * @param account 登陆名
     * @return SysUser
     */
    @Query(value = "select * from sys_user where account = :account")
    Optional<SysUser> findByAccount(@Param(value = "account") String account);

    /**
     * @param phone phone
     * @return SysUser
     */
    @Query(value = "select * from sys_user where phone = :phone")
    Optional<SysUser> findByPhone(@Param(value = "phone")String phone);

    /**
     * @param email email
     * @return SysUser
     */
    @Query(value = "select * from sys_user where email = :email")
    Optional<SysUser> findByEmail(@Param(value = "email")String email);
}
