package com.hk.pms.mappers;

import com.hk.data.mybatis.BaseMapper;
import com.hk.pms.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: kevin
 * @date: 2018-08-29 14:23
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleList(@Param("userId") String userId,@Param("appId") String appId);

    List<SysRole> getUserDeptRoleList(@Param("userId")String userId,@Param("appId") String appId);
}
