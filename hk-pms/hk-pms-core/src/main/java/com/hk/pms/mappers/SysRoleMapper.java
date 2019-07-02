package com.hk.pms.mappers;

import com.hk.data.ibatis.BaseMapper;
import com.hk.pms.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-29 14:23
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleList(Long userId, Long appId);

    List<SysRole> getUserDeptRoleList(Long userId, Long appId);
}
