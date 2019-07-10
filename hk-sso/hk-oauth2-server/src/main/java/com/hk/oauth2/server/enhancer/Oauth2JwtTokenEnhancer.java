package com.hk.oauth2.server.enhancer;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.EnumDisplayUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;
import com.hk.oauth2.server.entity.SysPermission;
import com.hk.oauth2.server.entity.SysRole;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import com.hk.oauth2.server.service.RoleService;
import com.hk.oauth2.server.service.SysPermissionService;
import com.hk.platform.commons.enums.SexEnum;
import lombok.extern.slf4j.Slf4j;
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
 * @author kevin
 * @date 2018-08-01 14:44
 */
@Slf4j
@Component
public class Oauth2JwtTokenEnhancer implements TokenEnhancer {

    private RoleService roleService;

    private SysPermissionService permissionService;

    private Oauth2ClientDetailsService oauth2ClientDetailsService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(SysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setSysAppService(Oauth2ClientDetailsService oauth2ClientDetailsService) {
        this.oauth2ClientDetailsService = oauth2ClientDetailsService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String clientId = authentication.getOAuth2Request().getClientId();
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> additionalInformation = defaultOAuth2AccessToken.getAdditionalInformation();
        Map<String, Object> info = new HashMap<>();
        Oauth2ClientDetails clientDetails = oauth2ClientDetailsService.getOne(Long.parseLong(clientId));
        info.put("userId", principal.getUserId());
        info.put("iconPath", principal.getIconPath());
        info.put("realName", principal.getRealName());
        info.put("userType", principal.getUserType());
        info.put("sex", principal.getSex());
        info.put("sexChinese", EnumDisplayUtils.getDisplayText(SexEnum.class, principal.getSex()));
//        if (!sysApp.getLocalApp()) {
//            Map<String, Object> infoMap = new HashMap<>(additionalInformation);
//            infoMap.putAll(info);
//            defaultOAuth2AccessToken.setAdditionalInformation(infoMap);
//            return defaultOAuth2AccessToken;
//        }
        info.put("appInfo", new ClientAppInfo(clientDetails.getId(), clientDetails.getAppCode(),
                clientDetails.getAppName(), clientDetails.getAppIcon()));
        info.put("account", principal.getAccount());
        info.put("email", principal.getEmail());
        info.put("phone", principal.getPhone());
        info.put("orgId", principal.getOrgId());
        info.put("orgName", principal.getOrgName());
        info.put("deptId", principal.getDeptId());
        info.put("deptName", principal.getDeptName());
        info.put("protectUser", principal.isProtectUser());

        final boolean debugEnabled = log.isDebugEnabled();
        if (debugEnabled) {
            log.debug("返回用户附加信息: {} ", info);
        }
        if (principal.isProtectUser() && debugEnabled) {
            log.debug("当前用户{}是系统保护用户，拥有所有角色与权限！ ", principal.getAccount());
        } else {
            Collection<String> roles = null;
            Set<String> permissions = null;
            List<SysRole> roleList = roleService.findRoleByAppIdAndUserId(clientDetails.getId(), principal.getUserId());
            if (CollectionUtils.isNotEmpty(roleList)) {
                if (debugEnabled) {
                    log.debug("查询到用户 {} 的角色:{}", principal.getAccount(), JsonUtils.serialize(roleList, true));
                }
                Map<Long, String> roleIdCodeMap = roleList.stream().collect(Collectors.toMap(SysRole::getId, SysRole::getRoleCode));
                roles = roleIdCodeMap.values();
                List<SysPermission> permissionList = permissionService.findByAppIdAndRoleIds(clientDetails.getId(), roleIdCodeMap.keySet());
                if (debugEnabled) {
                    log.debug("查询到用户 {} 的权限:{}", principal.getAccount(), JsonUtils.serialize(permissionList, true));
                }
                permissions = permissionList.stream().map(SysPermission::getPermissionCode).collect(Collectors.toSet());
            }
            info.put("roleSet", roles);
            info.put("permissionSet", permissions);
        }
        Map<String, Object> infoMap = new HashMap<>(additionalInformation);
        infoMap.putAll(info);
        defaultOAuth2AccessToken.setAdditionalInformation(infoMap);
        return accessToken;
    }
}
