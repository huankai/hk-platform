package com.hk.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.SpringContextHolder;
import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.UserDetailClientService;
import com.hk.oauth2.server.entity.SysApp;
import com.hk.oauth2.server.entity.SysOrg;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.service.SysAppService;
import com.hk.oauth2.server.service.SysOrgDeptService;
import com.hk.oauth2.server.service.SysOrgService;
import com.hk.oauth2.server.service.UserService;

/**
 * @author kevin
 * @date 2018-07-31 12:56
 */
@Component
public class SSOUserDetailServiceImpl implements UserDetailClientService {

    private final UserService userService;

    private SysAppService appService;

    @Autowired
    private SysOrgDeptService orgDeptService;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    public SSOUserDetailServiceImpl(UserService userService, SysAppService appService) {
        this.userService = userService;
        this.appService = appService;
    }

    @Override
    public SecurityUserPrincipal loadUserByLoginUsername(String username) {
        SysUser user = userService.findByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException(SpringContextHolder.getMessage("user.notFound.message", username)));
        SysOrg sysOrg = sysOrgService.getById(user.getOrgId());
        if (!ByteConstants.ONE.equals(sysOrg.getState())) {
            throw new DisabledException(SpringContextHolder.getMessage("org.disabled.message", sysOrg.getOrgName()));
        }
        return new SecurityUserPrincipal(user.getId(), user.getOrgId(), sysOrg.getOrgName(), user.getDeptId(), orgDeptService.getById(user.getDeptId()).getDeptName(),
                user.getAccount(), user.getIsProtect(), user.getRealName(),
                user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getPassword(), user.getUserStatus(), null, null);
    }

    @Override
    public ClientAppInfo getClientInfoById(String clientId) {
        SysApp app = appService.getById(clientId);
        return new ClientAppInfo(app.getId(), app.getAppCode(), app.getAppName(), app.getAppIcon());
    }
}
