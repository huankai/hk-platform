package com.hk.pms.service;

import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysUserCard;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:42
 */
public interface SysUserCardService extends JpaBaseService<SysUserCard, String> {

    Optional<SysUserCard> findByUserId(String userId);
}
