package com.hk.pms.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysUserCard;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:41
 */
public interface SysUserCardRepository extends StringIdJdbcRepository<SysUserCard> {

    Optional<SysUserCard> findByUserId(String userId);
}
