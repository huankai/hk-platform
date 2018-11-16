package com.hk.sso.server.config;

import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.authentication.security.handler.logout.RedirectLogoutHandler;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.SecurityAuthenticationAutoConfiguration;
import com.hk.core.autoconfigure.authentication.security.SmsAuthenticationSecurityConfiguration;
import com.hk.core.autoconfigure.authentication.security.ValidateCodeSecurityConfiguration;
import com.hk.core.autoconfigure.weixin.authentication.qrcode.WechatQrcodeAuthenticationSecurityConfigurer;
import com.hk.weixin.qrcode.WechatQrCodeConfig;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


/**
 *
 */
@Order(1)
@Configuration
@EnableWebSecurity
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


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
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
        http.apply(new WechatQrcodeAuthenticationSecurityConfigurer(wxMpService, qrCodeConfig));
        http
                .csrf().disable()

                .formLogin()

                .loginPage(browser.getLoginUrl()).permitAll() // 登陆 请求地址不需要认证可以访问，配置在这里
                .usernameParameter(browser.getUsernameParameter())
                .passwordParameter(browser.getPasswordParameter())
                .loginProcessingUrl(browser.getLoginProcessingUrl())

                .and()
//                .rememberMe().disable()//禁用remember-me功能
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .enableSessionUrlRewriting(false)
                .maximumSessions(browser.getMaximumSessions())
                .sessionRegistry(sessionRegistry())
                .maxSessionsPreventsLogin(browser.isMaxSessionsPreventsLogin())
                .and()
                .and()
                .logout().clearAuthentication(true)
                .logoutUrl(browser.getLogoutUrl())
                .invalidateHttpSession(true)
                .addLogoutHandler(new SecurityContextLogoutHandler())
                .addLogoutHandler(new RedirectLogoutHandler(browser.getLoginUrl()))
                .and()
                // 使用 zuul登陆地址
//                .addObjectPostProcessor(new ObjectPostProcessor<LoginUrlAuthenticationEntryPoint>() {
//                    @Override
//                    public <O extends LoginUrlAuthenticationEntryPoint> O postProcess(O object) {
//                        LoginUrlAuthenticationEntryPoint entryPoint = new LoginUrlAuthenticationEntryPoint(object.getLoginFormUrl()) {
//                            @Override
//                            protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
//                                return "http://127.0.0.1:8771/oauth2/login";
//                            }
//                        };
//                        return (O) entryPoint;
//                    }
//                });


                .authorizeRequests().expressionHandler(new AdminAccessWebSecurityExpressionHandler())// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
                /*.withObjectPostProcessor(new ObjectPostProcessor<AbstractSecurityExpressionHandler>() {
                    @Override
                    public <O extends AbstractSecurityExpressionHandler> O postProcess(O object) {
                        return (O) new AdminAccessWebSecurityExpressionHandler();
                    }
                })*/
//                @see https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("admin") //访问所有以 /actuator/**的需要有admin 角色
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/error", "/eureka/**", "/sms/sender", "/wechat/login", "/oauth/logout", "/favicon.ico");
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
