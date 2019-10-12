package com.hk.pms.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.domain.SysUserCard;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-08-31 11:42
 */
public interface SysUserCardService extends JdbcBaseService<SysUserCard, String> {

    Optional<SysUserCard> findByUserId(String userId);
}
