package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysUserThird;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: kevin
 * @date: 2018-04-12 16:43
 */
public interface SysUserThirdRepository extends StringIdJdbcRepository<SysUserThird> {

    @Modifying
    @Query(value = "DELETE FROM sys_user_third WHERE user_id = :userId AND account_type = :accountType")
    void deleteByUserIdAndAccountType(@Param("userId") String userId, @Param("accountType") byte bindAccountType);
}
