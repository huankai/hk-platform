package com.hk.sso.server.mappers;

import com.hk.sso.server.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author: kevin
 * @date: 2018-08-02 14:27
 */
@Mapper
public interface SysPermissionMapper {

    /**
     * 查询用户角色
     *
     * @param appId
     * @param roleIdSet
     * @return
     */
    List<SysPermission> findByAppIdAndRoleIds(@Param("appId") String appId, @Param("roleIdSet") Set<String> roleIdSet);
}
