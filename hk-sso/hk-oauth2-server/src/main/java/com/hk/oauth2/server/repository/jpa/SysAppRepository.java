package com.hk.oauth2.server.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.oauth2.server.entity.SysApp;

/**
 * @author kevin
 * @date 2018-08-02 16:53
 */
public interface SysAppRepository extends LongIdJpaRepository<SysApp> {

    SysApp getByClientId(String clientId);
}
