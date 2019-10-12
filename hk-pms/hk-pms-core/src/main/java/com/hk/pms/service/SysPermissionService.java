package com.hk.pms.service;


import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.data.jpa.domain.AbstractSnowflakeIdPersistable;
import com.hk.core.service.jpa.JpaBaseService;
import com.hk.pms.domain.SysPermission;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-04-12 16:53
 */
public interface SysPermissionService extends JpaBaseService<SysPermission, Long> {

    /**
     * 获取指定用户的所有权限
     *
     * @param userId userId
     * @return
     */
    List<SysPermission> getPermissionList(Long userId, Long appId);

    /**
     * 获取当前登陆用户的所有权限
     *
     * @return
     */
    default List<SysPermission> getCurrentUserPermissionList(Long appId) {
        UserPrincipal principal = SecurityContextUtils.getPrincipal();
        return principal.isAdministrator() ? Collections.emptyList() : getPermissionList(principal.getUserId(), appId);
    }

    /**
     * 用户是否有指定的权限
     *
     * @param userId         userId
     * @param appId          appId
     * @param permissionCode permissionCode
     * @return
     */
    default boolean hasPermission(Long userId, Long appId, String permissionCode) {
        return StringUtils.isNotBlank(permissionCode) && getPermissionListAsString(userId, appId).contains(permissionCode);
    }

    /**
     * 检查权限是否存在
     *
     * @param userId         用户id
     * @param appId          appId
     * @param permissionCode 权限编号
     */
    default void checkPermissioin(Long userId, Long appId, String permissionCode) {
        AssertUtils.isTrue(hasPermission(userId, appId, permissionCode), String.format("userId[%s],appId[%s], No Permission:[%s]", userId, appId, permissionCode));
    }

    /**
     * 返回当前登陆用户的所有系统权限编号列表
     *
     * @return
     */
    default Map<Long, Collection<String>> getCurrentUserAllRoleListAsString() {
        List<SysPermission> permissionList = getPermissionList(SecurityContextUtils.getPrincipal().getUserId(), null);
        Map<Long, List<SysPermission>> byAppIdMap = permissionList.stream().collect(Collectors.groupingBy(AbstractSnowflakeIdPersistable::getId));
        Map<Long, Collection<String>> result = new HashMap<>();
        byAppIdMap
                .forEach((key, value) -> result.put(key, value.stream().map(SysPermission::getPermissionCode).collect(Collectors.toList())));
        return result;
    }

    /**
     * 获取当前登陆用户的所有权限字符串
     *
     * @return
     */
    default List<String> getCurrentUserPermissionListAsString(Long appId) {
        return getPermissionListAsString(SecurityContextUtils.getPrincipal().getUserId(), appId);
    }

    /**
     * 获取指定用户的所有权限字符串
     *
     * @param userId userId
     * @param appId  appId
     * @return 权限列表
     */
    default List<String> getPermissionListAsString(Long userId, Long appId) {
        return getPermissionList(userId, appId)
                .stream()
                .map(SysPermission::getPermissionCode)
                .collect(Collectors.toList());
    }


}
