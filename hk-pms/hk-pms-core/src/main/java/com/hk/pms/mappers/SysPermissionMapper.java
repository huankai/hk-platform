package com.hk.pms.mappers;

import com.hk.data.ibatis.BaseMapper;
import com.hk.pms.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author kevin
 * @date 2018-08-29 14:23
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> getPermissionListByRoleIds(Collection<String> roleIds);

}
