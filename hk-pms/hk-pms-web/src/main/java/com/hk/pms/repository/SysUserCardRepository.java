package com.hk.pms.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysUserCard;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:41
 */
public interface SysUserCardRepository extends StringRepository<SysUserCard> {

    Optional<SysUserCard> findByUserId(String userId);
}
