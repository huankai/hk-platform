package com.hk.sso.server.config;

import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.SecurityAuthenticationAutoConfiguration;
import com.hk.core.autoconfigure.authentication.security.SmsAuthenticationSecurityConfiguration;
import com.hk.core.autoconfigure.authentication.security.ValidateCodeSecurityConfiguration;
import com.hk.core.autoconfigure.weixin.authentication.qrcode.WechatQrcodeAuthenticationSecurityConfigurer;
import com.hk.weixin.qrcode.WechatQrCodeConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 *
 */
@Order(1)
@Configuration
@EnableConfigurationProperties(value = {WechatQrCodeConfig.class, AuthenticationProperties.class})
public class SSOSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties authenticationProperties;

    @Autowired
    private WechatQrCodeConfig qrCodeConfig;

    /**
     * 手机号验证 Bean,在没有开启手机号验证时,不会注入该Bean
     *
     * @see SecurityAuthenticationAutoConfiguration.SmsAutoConfiguration
     */
    @Autowired(required = false)
    @Qualifier("smsValidateCodeProcessor")
    private ValidateCodeProcessor processor;

    public SSOSecurityWebAutoConfiguration(AuthenticationProperties authenticationProperties) {
        this.authenticationProperties = authenticationProperties;
    }

    /**
     * password Encoder
     *
     * @see SecurityAuthenticationAutoConfiguration#passwordEncoder()
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户Service
     *
     * @see com.hk.sso.server.service.impl.SSOUserDetailServiceImpl
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Autowired
    private WxMpService wxMpService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.BrowserProperties browser = authenticationProperties.getBrowser();
        AuthenticationProperties.SMSProperties sms = authenticationProperties.getSms();
        if (sms.isEnabled()) {
            http
                    .apply(new SmsAuthenticationSecurityConfiguration(sms, userDetailsService))
                    .and().apply(new ValidateCodeSecurityConfiguration(sms, processor, null));
        }
        http.apply(new WechatQrcodeAuthenticationSecurityConfigurer(wxMpService, qrCodeConfig));
        http
                .csrf().disable()

                .formLogin()
                .loginPage(browser.getLoginUrl()).permitAll() // 登陆 请求地址不需要认证可以访问，配置在这里
                .usernameParameter(browser.getUsernameParameter())
                .passwordParameter(browser.getPasswordParameter())
                .loginProcessingUrl(browser.getLoginProcessingUrl())

                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/sms/sender", "/wechat/login", "/oauth/logout", "/favicon.ico");
    }

    /**
     * 不定义没有password grant_type
     *
     * @return AuthenticationManager
     * @throws Exception exception
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
