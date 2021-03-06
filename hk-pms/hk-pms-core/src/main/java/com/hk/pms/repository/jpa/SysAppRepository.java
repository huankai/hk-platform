package com.hk.pms.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysApp;
import com.hk.pms.repository.jpa.custom.CustomSysAppRepository;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018-04-12 11:34
 */
public interface SysAppRepository extends LongIdJpaRepository<SysApp>, CustomSysAppRepository {

    Optional<SysApp> findByAppCode(String appCode);
}
