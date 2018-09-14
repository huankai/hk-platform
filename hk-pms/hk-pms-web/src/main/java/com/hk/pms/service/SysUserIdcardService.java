package com.hk.pms.service;

import com.hk.core.service.BaseService;
import com.hk.pms.domain.SysUserIdcard;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-08-31 11:42
 */
public interface SysUserIdcardService extends BaseService<SysUserIdcard, String> {

    Optional<SysUserIdcard> findByUserId(String userId);
}
