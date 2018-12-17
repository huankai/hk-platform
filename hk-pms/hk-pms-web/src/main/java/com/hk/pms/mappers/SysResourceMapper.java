package com.hk.pms.mappers;

import com.hk.pms.domain.SysResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author kevin
 * @date 2018-08-29 09:19
 */
@Mapper
public interface SysResourceMapper {

    List<SysResource> findByPermissionIds(Collection<String> permissions);
}
