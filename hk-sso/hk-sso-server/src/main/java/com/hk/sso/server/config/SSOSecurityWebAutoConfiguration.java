package com.hk.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.SecurityAuthenticationAutoConfiguration;
import com.hk.core.autoconfigure.authentication.security.SmsAuthenticationSecurityConfiguration;
import com.hk.core.autoconfigure.authentication.security.ValidateCodeSecurityConfiguration;


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
                .authorizeRequests()
                .antMatchers(browser.getLoginUrl()).permitAll() // 登陆 请求地址不需要认证可以访问，需要配置在这里
                .anyRequest().authenticated();

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/sms/sender", "/oauth/logout", "/favicon.ico");
        // loginUrl 配置在这里会有个问题，就是在登陆后再访问 /login 地址时，还是会跳转到登陆页，因为配置在这里的地址不会走Spring Security 的任意 Filter.
        // .antMatchers(HttpMethod.GET, properties.getBrowser().getLoginUrl());
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
