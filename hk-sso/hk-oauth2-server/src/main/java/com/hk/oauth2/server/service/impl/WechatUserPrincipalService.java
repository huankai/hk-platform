package com.hk.oauth2.server.service.impl;

import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.authentication.api.UserPrincipalService;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-05-11 23:14
 */
@Service(value = "wechatUserPrincipalService")
public class WechatUserPrincipalService implements UserPrincipalService<UserPrincipal, UserPrincipal> {

    @Override
    public UserPrincipal processAuthentication(UserPrincipal principal) {
        return principal;
    }
}
