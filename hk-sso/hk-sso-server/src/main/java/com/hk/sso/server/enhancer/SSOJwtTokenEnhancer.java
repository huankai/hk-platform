package com.hk.sso.server.enhancer;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.sso.server.entity.SysApp;
import com.hk.sso.server.entity.SysPermission;
import com.hk.sso.server.entity.SysRole;
import com.hk.sso.server.service.RoleService;
import com.hk.sso.server.service.SysAppService;
import com.hk.sso.server.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 返回用户附加信息
 *
 * @author: kevin
 * @date 2018-08-01 14:44
 */
@Component
public class SSOJwtTokenEnhancer implements TokenEnhancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSOJwtTokenEnhancer.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysAppService sysAppService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String clientId = authentication.getOAuth2Request().getClientId();
        SysApp sysApp = sysAppService.findOne(clientId).orElseThrow(() -> new RuntimeException("不存在的应用：" + clientId));
        if (!ByteConstants.ONE.equals(sysApp.getAppStatus())) {
            throw new RuntimeException("当前APP[ " + sysApp.getAppName() + "]已禁用,请与管理员联系！");
        }
        Map<String, Object> info = new HashMap<>();
        info.put("clientApp", new ClientAppInfo(sysApp.getId(), sysApp.getAppCode(), sysApp.getAppName(), sysApp.getAppIcon()));
        info.put("userId", principal.getUserId());
        info.put("account", principal.getAccount());
        info.put("email", principal.getEmail());
        info.put("iconPath", principal.getIconPath());
        info.put("realName", principal.getRealName());
        info.put("phone", principal.getPhone());
        info.put("sex", principal.getSex());
        info.put("userType", principal.getUserType());
        info.put("isProtect", principal.isProtectUser());
        boolean debugEnabled = LOGGER.isDebugEnabled();
        if (debugEnabled) {
            LOGGER.debug("返回用户附加信息: {} ", info);
        }
        if (principal.isProtectUser() && debugEnabled) {
            LOGGER.debug("当前用户{}是系统保护用户，拥有所有角色与权限！ ", principal.getAccount());
        } else {
            Collection<String> roles = null;
            Set<String> permissions = null;
            List<SysRole> roleList = roleService.findRoleByAppIdAndUserId(clientId, principal.getUserId());
            if (CollectionUtils.isNotEmpty(roleList)) {
                if (debugEnabled) {
                    LOGGER.debug("查询到用户 {} 的角色:{}", principal.getAccount(), JsonUtils.serialize(roleList, true));
                }
                Map<String, String> roleIdCodeMap = roleList.stream().collect(Collectors.toMap(SysRole::getId, SysRole::getRoleCode));
                roles = roleIdCodeMap.values();
                List<SysPermission> permissionList = permissionService.findByAppIdAndRoleIds(clientId, roleIdCodeMap.keySet());
                if (debugEnabled) {
                    LOGGER.debug("查询到用户 {} 的权限:{}", principal.getAccount(), JsonUtils.serialize(permissionList, true));
                }
                permissions = permissionList.stream().map(SysPermission::getPermissionCode).collect(Collectors.toSet());
            }
            info.put("roles", roles);
            info.put("permissionSet", permissions);
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
