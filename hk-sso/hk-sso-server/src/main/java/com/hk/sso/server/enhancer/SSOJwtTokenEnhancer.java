package com.hk.sso.server.enhancer;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.EnumDisplayUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.platform.commons.enums.SexEnum;
import com.hk.sso.server.entity.SysApp;
import com.hk.sso.server.entity.SysPermission;
import com.hk.sso.server.entity.SysRole;
import com.hk.sso.server.service.RoleService;
import com.hk.sso.server.service.SysAppService;
import com.hk.sso.server.service.SysPermissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 返回用户附加信息
 *
 * @author: kevin
 * @date: 2018-08-01 14:44
 */
@Component
@Log4j2
public class SSOJwtTokenEnhancer implements TokenEnhancer {

//    private static final Logger LOGGER = LoggerFactory.getLogger(SSOJwtTokenEnhancer.class);

    private RoleService roleService;

    private SysPermissionService permissionService;

    private SysAppService sysAppService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(SysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setSysAppService(SysAppService sysAppService) {
        this.sysAppService = sysAppService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String clientId = authentication.getOAuth2Request().getClientId();
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> additionalInformation = defaultOAuth2AccessToken.getAdditionalInformation();
        Map<String, Object> info = new HashMap<>();
        SysApp sysApp = sysAppService.findById(clientId).orElseThrow(() -> new OAuth2Exception("当前APP应用不存在"));
        if (!ByteConstants.ONE.equals(sysApp.getAppStatus())) {
            // 注意，这里要返回 400的异常状态码，如果不是，客户端很难获取到异常的详细信息
            throw new OAuth2Exception("你访问的应用[ " + sysApp.getAppName() + "]已禁用,请与管理员联系！");
        }
        info.put("userId", principal.getUserId());
        info.put("iconPath", principal.getIconPath());
        info.put("realName", principal.getRealName());
        info.put("userType", principal.getUserType());
        info.put("sex", principal.getSex());
        info.put("sexChinese", EnumDisplayUtils.getDisplayText(SexEnum.class, principal.getSex(), false));
        if (!sysApp.getLocalApp()) {
            Map<String, Object> infoMap = new HashMap<>(additionalInformation);
            infoMap.putAll(info);
            defaultOAuth2AccessToken.setAdditionalInformation(infoMap);
            return defaultOAuth2AccessToken;
        }
        info.put("appInfo", new ClientAppInfo(sysApp.getId(), sysApp.getAppCode(), sysApp.getAppName(), sysApp.getAppIcon()));
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
            List<SysRole> roleList = roleService.findRoleByAppIdAndUserId(clientId, principal.getUserId());
            if (CollectionUtils.isNotEmpty(roleList)) {
                if (debugEnabled) {
                    log.debug("查询到用户 {} 的角色:{}", principal.getAccount(), JsonUtils.serialize(roleList, true));
                }
                Map<String, String> roleIdCodeMap = roleList.stream().collect(Collectors.toMap(SysRole::getId, SysRole::getRoleCode));
                roles = roleIdCodeMap.values();
                List<SysPermission> permissionList = permissionService.findByAppIdAndRoleIds(clientId, roleIdCodeMap.keySet());
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
