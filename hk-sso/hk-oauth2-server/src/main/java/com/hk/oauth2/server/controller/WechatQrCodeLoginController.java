package com.hk.oauth2.server.controller;

import com.hk.weixin.qrcode.WechatQrCodeConfig;
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

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatQrCodeConfig config;

    /**
     * 二维码登陆地址
     *
     * @param response response
     * @throws IOException
     */
    @GetMapping(path = "login")
    public void wechatLogin(HttpServletResponse response) throws IOException {
        final String callbackUrl = String.format("%s%s", config.getCallHost(), config.getCallbackUrl());
        String connectUrl = wxMpService.buildQrConnectUrl(callbackUrl, "snsapi_login",
                config.getState());
        response.sendRedirect(connectUrl);
    }
}
