package com.hk.sso.server.service.impl;

import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.UserDetailClientService;
import com.hk.core.exception.ServiceException;
import com.hk.sso.server.entity.SysApp;
import com.hk.sso.server.entity.SysUser;
import com.hk.sso.server.service.SysAppService;
import com.hk.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author: kevin
 * @date: 2018-07-31 12:56
 */
@Component
public class SSOUserDetailServiceImpl implements UserDetailClientService {

    private final UserService userService;

    private SysAppService appService;

    @Autowired
    public SSOUserDetailServiceImpl(UserService userService, SysAppService appService) {
        this.userService = userService;
        this.appService = appService;
    }

    @Override
    public SecurityUserPrincipal loadUserByLoginUsername(String username) {
        SysUser user = userService.findByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException("不存在的用户:" + username));
        return new SecurityUserPrincipal(user.getId(), user.getAccount(), user.getIsProtect(), user.getRealName(),
                user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getPassword(), user.getUserStatus());
    }

    @Override
    public ClientAppInfo getClientInfoById(String clientId) {
        SysApp app = appService.findOne(clientId).orElseThrow(() -> new ServiceException("当前APP应用不存在"));
        return new ClientAppInfo(app.getId(), app.getAppCode(), app.getAppName(), app.getAppIcon());
    }
}
