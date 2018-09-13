package com.hk.pms.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysUserThird;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: kevin
 * @date 2018-04-12 16:43
 */
public interface SysUserThirdRepository extends StringRepository<SysUserThird> {

    @Modifying
    @Query(value = "DELETE FROM sys_user_third WHERE user_id = ?1 AND account_type = ?2", nativeQuery = true)
    void deleteByUserIdAndAccountType(String userId, byte bindAccountType);
}
