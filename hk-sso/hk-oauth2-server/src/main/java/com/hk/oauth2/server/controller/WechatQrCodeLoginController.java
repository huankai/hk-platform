package com.hk.oauth2.server.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.weixin.qrcode.WechatQrCodeProperties;

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
    private WechatQrCodeProperties qrCodeProperties;

    /**
     * 二维码登陆地址
     *
     * @param response response
     * @throws IOException
     */
    @GetMapping(path = "login")
    public void wechatLogin(HttpServletResponse response) throws IOException {
        final String callbackUrl = String.format("%s%s", qrCodeProperties.getCallHost(), qrCodeProperties.getCallbackUrl());
        String connectUrl = wxMpService.buildQrConnectUrl(callbackUrl, "snsapi_login",
        		qrCodeProperties.getState());
        response.sendRedirect(connectUrl);
    }
}
