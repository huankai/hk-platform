package com.hk.oauth2.server.web.authentication;

import com.hk.core.autoconfigure.alipay.AlipayConstants;
import com.hk.core.autoconfigure.alipay.AlipayProperties;
import com.hk.core.autoconfigure.weixin.WeiXinMpProperties;
import com.hk.core.web.Webs;
import lombok.Setter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登陆
 * 如果是微信请求，并开启了微信登陆，跳转到微信登陆
 * 如果是支付宝请求，并开启了支付宝登陆，跳转到支付宝登陆
 *
 * @author kevin
 * @date 2019-11-14 16:30
 */
public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    @Setter
    private WeiXinMpProperties weiXinMpProperties;

    @Setter
    private AlipayProperties alipayProperties;

    @Setter
    private WxMpService wxMpService;

    public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (isWeiXinRequest(request)) {
            WeiXinMpProperties.Authentication authentication = weiXinMpProperties.getAuthentication();
            String authorizationUrl = String.format("%s%s%s", authentication.getCallHost(), request.getContextPath(), authentication.getCallbackUrl());
            String url = wxMpService.oauth2buildAuthorizationUrl(authorizationUrl, authentication.getScope(),
                    authentication.getState());
            response.sendRedirect(url);
            return;
        } else if (isAliRequest(request)) {
            String publicAppAuthorizeUrl = AlipayConstants.getPublicAppAuthorizeUrl(alipayProperties);
            response.sendRedirect(publicAppAuthorizeUrl);
            return;
        }
        super.commence(request, response, authException);
    }

    /**
     * 是否为支付宝应用请求
     */
    private boolean isAliRequest(HttpServletRequest request) {
        return alipayProperties != null && alipayProperties.isEnabled() && Webs.isAliPay(request);
    }

    /**
     * 是否为微信设备请求
     */
    private boolean isWeiXinRequest(HttpServletRequest request) {
        return weiXinMpProperties != null && wxMpService != null
                && weiXinMpProperties.isEnabled() && Webs.isWeiXin(request);
    }
}
