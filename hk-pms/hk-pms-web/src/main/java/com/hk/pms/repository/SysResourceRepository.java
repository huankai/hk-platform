package com.hk.pms.repository;

import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.pms.domain.SysResource;

import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-28 16:37
 */
public interface SysResourceRepository extends StringRepository<SysResource> {

    List<SysResource> findByAppIdOrderByOrderedAsc(String appId);

}
