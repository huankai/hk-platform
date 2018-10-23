package com.hk.pms.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysApp;

import java.util.Optional;

/**
 * @author: kevin
 * @date: 2018-04-12 11:34
 */
public interface SysAppRepository extends StringIdJdbcRepository<SysApp> {

    Optional<SysApp> findByAppCode(String appCode);
}
