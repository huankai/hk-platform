package com.hk.oauth2.server.service.impl;

import com.hk.core.authentication.api.PostAuthenticaionHandler;
import com.hk.core.authentication.api.UserPrincipal;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-05-11 23:14
 */
@Service(value = "wechatPostAuthenticaionHandler")
public class WechatPostAuthenticaionHandler implements PostAuthenticaionHandler<UserPrincipal, UserPrincipal> {

    @Override
    public UserPrincipal handler(UserPrincipal principal) {
        return principal;
    }
}
