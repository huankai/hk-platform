package com.hk.pms.repository.jdbc;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.pms.domain.SysResource;

/**
 * @author kevin
 * @date 2018-08-28 16:37
 */
public interface SysResourceRepository extends StringIdJdbcRepository<SysResource> {

	@Query(value = "SELECT id,parent_id,app_id,resource_name,resource_uri,resource_type,ordered,icon,remark,state,target,"
			+ "created_by,created_date,last_modified_by,last_modified_date "
			+ "FROM sys_resource WHERE app_id = :appId AND state = 1 ORDER BY ordered ASC")
    List<SysResource> findByAppIdOrderByOrderedAsc(@Param(value = "appId")String appId);

}
