package com.hk.pms.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysUserIdcard;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:41
 */
public interface SysUserIdcardRepository extends StringRepository<SysUserIdcard> {

    Optional<SysUserIdcard> findByUserId(String userId);
}
