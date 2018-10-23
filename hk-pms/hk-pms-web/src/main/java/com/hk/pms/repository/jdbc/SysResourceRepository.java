package com.hk.pms.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysResource;

import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-28 16:37
 */
public interface SysResourceRepository extends StringIdJdbcRepository<SysResource> {

    List<SysResource> findByAppIdOrderByOrderedAsc(String appId);

}
