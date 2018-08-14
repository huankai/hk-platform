package com.hk.sso.server.config;

import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.SecurityAuthenticationAutoConfiguration;
import com.hk.core.autoconfigure.authentication.security.SmsAuthenticationSecurityConfiguration;
import com.hk.core.autoconfigure.authentication.security.ValidateCodeSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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
public class SSOSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    public SSOSecurityWebAutoConfiguration(AuthenticationProperties properties) {
        this.properties = properties;
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

    /**
     * 手机号验证 Bean,在没有开启手机号验证时,不会注入该Bean
     *
     * @see SecurityAuthenticationAutoConfiguration.SmsAutoConfiguration
     */
    @Autowired(required = false)
    @Qualifier("smsValidateCodeProcessor")
    private ValidateCodeProcessor processor;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.BrowserProperties browser = properties.getBrowser();
        AuthenticationProperties.SMSProperties sms = properties.getSms();
        if (sms.isEnabled()) {
            http
                    .apply(new SmsAuthenticationSecurityConfiguration(sms, userDetailsService))
                    .and().apply(new ValidateCodeSecurityConfiguration(sms, processor, null));
        }
        http
                .csrf().disable()

                .formLogin()
                .loginPage(browser.getLoginUrl())
                .usernameParameter(browser.getUsernameParameter())
                .passwordParameter(browser.getPasswordParameter())
                .loginProcessingUrl(browser.getLoginProcessingUrl())

                .and()
                .authorizeRequests().anyRequest().authenticated();


    }


//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .formLogin().loginPage(browser.getLoginUrl())
//                .usernameParameter(browser.getUsernameParameter())
//                .passwordParameter(browser.getPasswordParameter())
//
//                .and()
//                .authorizeRequests().anyRequest().authenticated();
////        http.csrf().disable()
////                .formLogin()
////                .loginPage(browser.getLoginUrl()) // 如果使用自定义登陆页面，不能禁用匿名用户，会出现循环重定向
////                .usernameParameter(browser.getUsernameParameter())
////                .passwordParameter(browser.getPasswordParameter());
////        AuthenticationProperties.SMSProperties sms = properties.getSms();
////        if (sms.isEnabled()) {
////            /**
////             如果开启短信验证功能,需要将发送验证码的接口配置为任意可以访问.
////             {@link com.hk.sso.server.rest.SMSValidateCodeController}
////
////             */
////            http.apply(new SmsAuthenticationSecurityConfiguration(sms, userDetailsService))
////                    .and().apply(new ValidateCodeSecurityConfiguration(sms, processor, null))
////                    .and().authorizeRequests().antMatchers("/code/sms").permitAll();
////        }
////        http.authorizeRequests().anyRequest().permitAll();
//    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/sms/sender", "/oauth/logout", "/favicon.ico").antMatchers(HttpMethod.GET, properties.getBrowser().getLoginUrl());
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
