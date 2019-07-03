package com.hk.oauth2.server.service.impl;

import com.hk.core.authentication.api.PostAuthenticationHandler;
import com.hk.core.authentication.api.UserPrincipal;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-05-11 23:14
 */
@Service(value = "wechatPostAuthenticationHandler")
public class WechatPostAuthenticationHandler implements PostAuthenticationHandler<UserPrincipal, WxMpUser> {

    @Override
    public UserPrincipal handler(WxMpUser wxMpUser) {
        return new UserPrincipal();
    }
}
