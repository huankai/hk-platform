package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.SpringContextHolder;
import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.UserDetailClientService;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;
import com.hk.oauth2.server.entity.SysOrg;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import com.hk.oauth2.server.service.SysOrgDeptService;
import com.hk.oauth2.server.service.SysOrgService;
import com.hk.oauth2.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kevin
 * @date 2018-07-31 12:56
 */
@Component(value = "ssoUserDetailService")
public class SSOUserDetailServiceImpl implements UserDetailClientService {

    private final UserService userService;

    private Oauth2ClientDetailsService oauth2ClientDetailsService;

    @Autowired
    private SysOrgDeptService orgDeptService;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    public SSOUserDetailServiceImpl(UserService userService, Oauth2ClientDetailsService oauth2ClientDetailsService) {
        this.userService = userService;
        this.oauth2ClientDetailsService = oauth2ClientDetailsService;
    }

    @Override
    @Transactional
    public SecurityUserPrincipal loadUserByLoginUsername(String username) {
        SysUser user = userService.findByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException(SpringContextHolder.getMessage("user.notFound.message", username)));
        SysOrg sysOrg = sysOrgService.findById(user.getOrgId())
                .orElseThrow(() -> new AuthorizationServiceException("该机构不存在 !"));
        if (!ByteConstants.ONE.equals(sysOrg.getState())) {
            throw new DisabledException(SpringContextHolder.getMessage("org.disabled.message", sysOrg.getOrgName()));
        }
        return new SecurityUserPrincipal(user.getId(), user.getOrgId(), sysOrg.getOrgName(), user.getDeptId(), orgDeptService.getOne(user.getDeptId()).getDeptName(),
                user.getAccount(), user.getIsProtect(), user.getRealName(),
                user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getPassword(), user.getUserStatus(), null, null);
    }

    @Override
    @Transactional
    public ClientAppInfo getClientInfoById(Long clientId) {
        Oauth2ClientDetails clientDetails = oauth2ClientDetailsService.getOne(clientId);
        return new ClientAppInfo(clientDetails.getId(), clientDetails.getAppCode(), clientDetails.getAppName(), clientDetails.getAppIcon());
    }
}
