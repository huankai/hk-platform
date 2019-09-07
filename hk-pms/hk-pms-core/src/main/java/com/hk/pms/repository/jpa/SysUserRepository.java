package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 16:42
 */
public interface SysUserRepository extends LongIdJpaRepository<SysUser> {

    /**
     * @param account 登陆名
     * @return SysUser
     */
//    @Query(value = "select * from sys_user where account = :account")
    Optional<SysUser> findByAccount(String account);

    /**
     * @param phone phone
     * @return SysUser
     */
//    @Query(value = "select * from sys_user where phone = :phone")
    Optional<SysUser> findByPhone(String phone);

    /**
     * @param email email
     * @return SysUser
     */
//    @Query(value = "select * from sys_user where email = :email")
    Optional<SysUser> findByEmail(String email);

    @Modifying
    @Query(value = "UPDATE sys_user SET password = ?2 WHERE id = ?1", nativeQuery = true)
    int updatePassword(Long id, String newPassword);
}
