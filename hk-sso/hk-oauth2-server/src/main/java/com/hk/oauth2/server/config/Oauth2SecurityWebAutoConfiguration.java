package com.hk.oauth2.server.config;

import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.authentication.security.handler.logout.RedirectLogoutHandler;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.SecurityAuthenticationAutoConfiguration;
import com.hk.core.autoconfigure.authentication.security.SmsAuthenticationSecurityConfiguration;
import com.hk.core.autoconfigure.authentication.security.ValidateCodeSecurityConfiguration;
import com.hk.oauth2.server.constants.ClientEquipment;
import com.hk.oauth2.server.service.impl.SSOUserDetailServiceImpl;
import com.hk.platform.commons.role.RoleNamed;
import com.hk.weixin.WechatMpProperties;
import com.hk.weixin.security.WechatAuthenticationSecurityConfigurer;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.ObjectProvider;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 */
@Order(1)
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(value = {WechatMpProperties.class, AuthenticationProperties.class})
public class Oauth2SecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {


    private AuthenticationProperties authenticationProperties;

    private WechatMpProperties wechatProperties;

    private WxMpService wxMpService;

    /**
     * 手机号验证 Bean,在没有开启手机号验证时,不会注入该Bean
     *
     * @see SecurityAuthenticationAutoConfiguration.SmsAutoConfiguration
     */

    private ValidateCodeProcessor processor;

    public Oauth2SecurityWebAutoConfiguration(AuthenticationProperties authenticationProperties,
                                              WechatMpProperties wechatProperties,
                                              ObjectProvider<WxMpService> wxMpServices,
                                              @Qualifier("smsValidateCodeProcessor") ObjectProvider<ValidateCodeProcessor> validateCodeProcessors) {
        this.authenticationProperties = authenticationProperties;
        this.wechatProperties = wechatProperties;
        this.wxMpService = wxMpServices.getIfAvailable();
        this.processor = validateCodeProcessors.getIfAvailable();
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
     * @see SSOUserDetailServiceImpl
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.LoginProperties login = authenticationProperties.getLogin();
        AuthenticationProperties.SMSProperties sms = authenticationProperties.getSms();
        if (sms.isEnabled()) {
            http
                    .apply(new SmsAuthenticationSecurityConfiguration(sms, userDetailsService))
                    .and().apply(new ValidateCodeSecurityConfiguration(sms, processor, null));
        }
        if (wechatProperties.isEnabled()) {
            if (null == wxMpService) {
                throw new NullPointerException("wechat is enabled ,But wxMpService is null.");
            }
            http.apply(new WechatAuthenticationSecurityConfigurer(wxMpService, wechatProperties.getAuthentication()));
        }
        http
                .csrf().disable()

                .formLogin()

                .loginPage(login.getLoginUrl()).permitAll() // 登陆 请求地址不需要认证可以访问，配置在这里
                .usernameParameter(login.getUsernameParameter())
                .passwordParameter(login.getPasswordParameter())
                .loginProcessingUrl(login.getLoginProcessingUrl())

                .and()
//                .rememberMe().disable()//禁用remember-me功能
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .enableSessionUrlRewriting(false)
                .maximumSessions(login.getMaximumSessions())
                .sessionRegistry(sessionRegistry())
                .expiredUrl(login.getSessionInvalidUrl())
                .maxSessionsPreventsLogin(login.isMaxSessionsPreventsLogin())
                .and()
                .and()
                .logout().clearAuthentication(true)
                .logoutUrl(login.getLogoutUrl())
                .invalidateHttpSession(true)
                .addLogoutHandler(new RedirectLogoutHandler(login.getLogoutSuccessUrl()))
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
                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(login.getLoginUrl()) {

            @Override
            protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
                String value = request.getParameter(ClientEquipment.CLIENT_EQUIPMENT_PARAMETER_NAME);
                String url = super.determineUrlToUseForThisRequest(request, response, exception);
                if (StringUtils.equalsIgnoreCase(value, ClientEquipment.PHONE)) {//如果是手机端访问，跳转到手机端登陆页
                    url = String.format("/%s%s", ClientEquipment.CLIENT_EQUIPMENT_PARAMETER_NAME, url);
                }
                return url;
            }
        }).and()


                .authorizeRequests().expressionHandler(new AdminAccessWebSecurityExpressionHandler())// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
//                @see https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole(RoleNamed.ADMIN) //访问所有以 /actuator/**的需要有admin 角色
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**",
                "email/register/**",// 邮箱号注册
                "/error", // 错误页面
                "/actuator/health",  // 健康检查
                "/sms/sender",  // 短信登陆
                "/wechat/login", // 微信登陆
                "/favicon.ico"); // ico
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
