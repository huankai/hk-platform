package com.hk.oauth2.server.controller;

import com.hk.core.autoconfigure.weixin.WeiXinMpProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kally
 * @date 2018年2月7日下午1:56:15
 */
@Controller
@RequestMapping("wechat")
public class WechatQrCodeLoginController {

    @Autowired(required = false)
    private WxMpService wxMpService;

    @Autowired
    private WeiXinMpProperties weiXinMpProperties;

    /**
     * 二维码登陆地址
     *
     * @param response response
     * @throws IOException
     */
    @GetMapping(path = "login")
    public void wechatLogin(HttpServletResponse response) throws IOException {
        if (weiXinMpProperties.isEnabled() && null != wxMpService) {
            WeiXinMpProperties.Authentication authentication = weiXinMpProperties.getAuthentication();
            final String callbackUrl = String.format("%s%s", authentication.getCallHost(), authentication.getCallbackUrl());
            String connectUrl = wxMpService.buildQrConnectUrl(callbackUrl, "snsapi_login",
                    authentication.getState());
            response.sendRedirect(connectUrl);
        }
        throw new RuntimeException("不支持的认证");
    }
}
