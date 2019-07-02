package com.hk.pms.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysUserCard;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-08-31 11:41
 */
public interface SysUserCardRepository extends LongIdJpaRepository<SysUserCard> {

    Optional<SysUserCard> findByUserId(Long userId);
}
