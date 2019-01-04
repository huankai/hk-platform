package com.hk.pms.service;


import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.pms.domain.SysRole;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-04-12 16:59
 */
public interface SysRoleService extends JdbcBaseService<SysRole, String> {


    /**
     * 获取指定用户的所有角色
     *
     * @param userId userId
     * @param appId  appId
     * @return
     */
    List<SysRole> getRoleList(String userId, String appId);

    /**
     * 用户是否有指定的角色
     *
     * @param userId   用户id
     * @param appId    appId
     * @param roleCode roleCode
     * @return
     */
    default boolean hasRole(String userId, String appId, String roleCode) {
        return StringUtils.isNotEmpty(roleCode) && getRoleListAsString(userId, appId).contains(roleCode);
    }

    /**
     * 判断用户是否有指定的角色，如果没有抛出异常
     *
     * @param userId   userId
     * @param appId    appId
     * @param roleCode 角色编号
     */
    default void checkRole(String userId, String appId, String roleCode) {
        AssertUtils.isTrue(hasRole(userId, appId, roleCode), String.format("userId[%s],appId[%s], No role:[%s]", userId, appId, roleCode));
    }

    /**
     * @return
     */
    default Map<String, Collection<String>> getCurrentUserAllRoleListAsString() {
        List<SysRole> roleList = getRoleList(SecurityContextUtils.getPrincipal().getUserId(), null);
        Map<String, List<SysRole>> byAppIdMap = roleList.stream().collect(Collectors.groupingBy(SysRole::getId));
        Map<String, Collection<String>> result = new HashMap<>();
        byAppIdMap.forEach((key, value) -> result.put(key, value.stream().map(SysRole::getRoleCode).collect(Collectors.toList())));
        return result;
    }

    /**
     * @param appId appId
     * @return
     */
    default List<String> getCurrentUserRoleListAsString(String appId) {
        return getRoleListAsString(SecurityContextUtils.getPrincipal().getUserId(), appId);
    }

    /**
     * @param userId 用户id
     * @param appId  appId
     * @return
     */
    default List<String> getRoleListAsString(String userId, String appId) {
        return getRoleList(userId, appId)
                .stream()
                .map(SysRole::getRoleCode)
                .collect(Collectors.toList());
    }

    /**
     * 获取当前登陆用户的所有角色
     *
     * @return
     */
    default List<SysRole> getCurrentUserRoleList(String appId) {
        return getRoleList(SecurityContextUtils.getPrincipal().getUserId(), appId);
    }

    /**
     * 禁用
     *
     * @param id
     */
    void disable(String id);

    /**
     * 启用
     *
     * @param id
     */
    void enable(String id);
}
