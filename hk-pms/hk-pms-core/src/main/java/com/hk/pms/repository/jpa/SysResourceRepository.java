package com.hk.pms.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.pms.domain.SysResource;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-28 16:37
 */
public interface SysResourceRepository extends LongIdJpaRepository<SysResource> {

//	@Query(value = "SELECT id,parent_id,app_id,resource_name,resource_uri,resource_type,ordered,icon,remark,state,target,"
//			+ "created_by,created_date,last_modified_by,last_modified_date "
//			+ "FROM sys_resource WHERE app_id = :appId AND state = 1 ORDER BY ordered ASC")
    List<SysResource> findByAppIdOrderByOrderedAsc(Long appId);

}
