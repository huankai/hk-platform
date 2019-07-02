package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysUserThird;
import org.springframework.data.jpa.repository.Modifying;

/**
 * @author kevin
 * @date 2018-04-12 16:43
 */
public interface SysUserThirdRepository extends LongIdJpaRepository<SysUserThird> {

    @Modifying
//    @Query(value = "DELETE FROM sys_user_third WHERE user_id = :userId AND account_type = :accountType")
    void deleteByUserIdAndAccountType(Long userId, byte bindAccountType);
}
