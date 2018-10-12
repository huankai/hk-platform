package com.hk.pms.repository.jpa;

import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.pms.domain.SysUserCard;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:41
 */
public interface SysUserCardRepository extends StringIdJpaRepository<SysUserCard> {

    Optional<SysUserCard> findByUserId(String userId);
}
