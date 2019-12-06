package com.hk.oauth2.server.service.impl;

import com.hk.core.authentication.api.PostAuthenticationHandler;
import com.hk.core.authentication.api.UserPrincipal;
import org.springframework.stereotype.Component;

/**
 * @author kevin
 * @date 2019-7-19 14:03
 */
@Component(value = "alipayPostAuthenticationHandler")
public class AlipayPostAuthenticationHandler implements PostAuthenticationHandler<UserPrincipal, String> {

    @Override
    public UserPrincipal handler(String userId) {
        return new UserPrincipal();
    }
}
