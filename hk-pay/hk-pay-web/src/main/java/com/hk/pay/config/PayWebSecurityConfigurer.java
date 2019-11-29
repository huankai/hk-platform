package com.hk.pay.config;

import com.alipay.api.AlipayClient;
import com.hk.core.autoconfigure.alipay.AlipayProperties;
import com.hk.core.autoconfigure.authentication.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.WeiXinAuthenticationSecurityConfigurer;
import com.hk.core.autoconfigure.weixin.WeiXinMpProperties;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author kevin
 * @date 2019-11-28 17:30
 */
@Configuration
@EnableConfigurationProperties(value = {WeiXinMpProperties.class, AlipayProperties.class, AuthenticationProperties.class})
public class PayWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private WeiXinMpProperties weiXinMpProperties;

    private AlipayProperties alipayProperties;

    private AuthenticationProperties authenticationProperties;

    private WxMpService wxMpService;

    private AlipayClient alipayClient;

    public PayWebSecurityConfigurer(WeiXinMpProperties weiXinMpProperties,
                                    AlipayProperties alipayProperties,
                                    AuthenticationProperties authenticationProperties,
                                    ObjectProvider<WxMpService> wxMpService,
                                    ObjectProvider<AlipayClient> alipayClient) {
        this.weiXinMpProperties = weiXinMpProperties;
        this.alipayProperties = alipayProperties;
        this.authenticationProperties = authenticationProperties;
        this.wxMpService = wxMpService.getIfAvailable();
        this.alipayClient = alipayClient.getIfAvailable();
    }

    private void configureWeChat(HttpSecurity http) throws Exception {
        if (weiXinMpProperties.isEnabled()) {
            if (null == wxMpService) {
                throw new NullPointerException("weChat is enabled ,But wxMpService is null.");
            }
            http.apply(new WeiXinAuthenticationSecurityConfigurer(wxMpService, weiXinMpProperties.getAuthentication(), null));
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
