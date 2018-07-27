package com.hk.sso.server.repository;

import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.sso.server.entity.SysUser;

import java.util.Optional;

/**
 * @author: kevin
 * @date 2018-07-25 08:49
 */
public interface UserRepository extends BaseRepository<SysUser, String> {

    /**
     * @param phone 手机号
     * @return
     */
    Optional<SysUser> findByPhone(String phone);
}
