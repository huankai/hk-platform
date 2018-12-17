package com.hk.oauth2.server.mappers;


import com.hk.oauth2.server.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-02 14:27
 */
@Mapper
public interface SysRoleMapper {

    /**
     * 查询用户角色
     *
     * @param appId  appId
     * @param userId userId
     * @return sysRole
     */
    List<SysRole> findRoleByAppIdAndUserId(@Param("appId") String appId, @Param("userId") String userId);
}
