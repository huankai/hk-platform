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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 *
 */
@Order(1)
@Configuration
@EnableWebSecurity
public class SSOSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties authenticationProperties;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.BrowserProperties browser = authenticationProperties.getBrowser();
        AuthenticationProperties.SMSProperties sms = authenticationProperties.getSms();
        if (sms.isEnabled()) {
            http
                    .apply(new SmsAuthenticationSecurityConfiguration(sms, userDetailsService))
                    .and().apply(new ValidateCodeSecurityConfiguration(sms, processor, null));
        }
//        DefaultSecurityFilterChain defaultSecurityFilterChain = http.objectPostProcessor(new ObjectPostProcessor<SecurityContextPersistenceFilter>() {
//            @Override
//            public <O extends SecurityContextPersistenceFilter> O postProcess(O object) {
//                return null;
//            }
//        });
        http
                .csrf().disable()

                //.getConfigurer(SecurityContextPersistenceFilter.class)
//                .addFilterAfter(null,SecurityContextPersistenceFilter.class)

                .formLogin()
                .loginPage(browser.getLoginUrl()).permitAll()
                .usernameParameter(browser.getUsernameParameter())
                .passwordParameter(browser.getPasswordParameter())
                .loginProcessingUrl(browser.getLoginProcessingUrl())

                .and()
                .authorizeRequests()
//                .antMatchers(browser.getLoginUrl()).permitAll() // 登陆 请求地址不需要认证可以访问，需要配置在这里
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/sms/sender", "/oauth/logout", "/favicon.ico");
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
