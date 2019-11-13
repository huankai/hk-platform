package com.hk.oauth2.server.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.PostAuthenticationHandler;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.authentication.security.handler.login.LoginAuthenticationFailureHandler;
import com.hk.core.authentication.security.handler.logout.EquipmentLogoutHandler;
import com.hk.core.autoconfigure.alipay.AlipayProperties;
import com.hk.core.autoconfigure.authentication.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.*;
import com.hk.core.autoconfigure.weixin.WeiXinMpProperties;
import com.hk.core.web.Webs;
import com.hk.oauth2.TokenRegistry;
import com.hk.oauth2.authentication.session.CreateSessionAuthenticationStrategy;
import com.hk.oauth2.http.HttpClient;
import com.hk.oauth2.logout.DefaultSingleLogoutServiceMessageHandler;
import com.hk.oauth2.logout.SingleLogoutHandler;
import com.hk.oauth2.server.service.impl.SSOUserDetailServiceImpl;
import com.hk.oauth2.web.authentication.PhoneAuthenticationSuccessHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;


@Order(1)
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(value = {WeiXinMpProperties.class, AlipayProperties.class, AuthenticationProperties.class})
public class Oauth2SecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties authenticationProperties;

    private WeiXinMpProperties weiXinMpProperties;

    private WxMpService wxMpService;

    private AlipayProperties alipayProperties;

    private final AlipayClient alipayClient;

    /**
     * 手机号验证 Bean,在没有开启手机号验证时,不会注入该Bean
     *
     * @see SecurityAuthenticationAutoConfiguration
     */
    private ValidateCodeProcessor validateCodeProcessor;

    private HttpClient httpClient;

    private TokenRegistry tokenRegistry;

    private ApplicationContext applicationContext;

    public Oauth2SecurityWebAutoConfiguration(AuthenticationProperties authenticationProperties,
                                              AlipayProperties alipayProperties,
                                              WeiXinMpProperties weiXinMpProperties,
                                              ObjectProvider<WxMpService> wxMpServices,
                                              ObjectProvider<AlipayClient> alipayClients,
                                              ApplicationContext applicationContext,
                                              @Qualifier("smsValidateCodeProcessor") ObjectProvider<ValidateCodeProcessor> validateCodeProcessors) {
        this.authenticationProperties = authenticationProperties;
        this.alipayProperties = alipayProperties;
        this.weiXinMpProperties = weiXinMpProperties;
        this.wxMpService = wxMpServices.getIfAvailable();
        this.alipayClient = alipayClients.getIfAvailable();
        this.applicationContext = applicationContext;
        this.httpClient = applicationContext.getBean(HttpClient.class);
        this.tokenRegistry = applicationContext.getBean(TokenRegistry.class);
        this.validateCodeProcessor = validateCodeProcessors.getIfAvailable();
    }

    /**
     * @see CreateSessionAuthenticationStrategy
     */
    @Bean
    public ApplicationListener<SessionFixationProtectionEvent> sessionFixationProtectionEvent() {
        return event -> {
            AbstractAuthenticationToken authentication = (AbstractAuthenticationToken) event.getAuthentication();
            authentication.setDetails(new WebAuthenticationDetails(Webs.getHttpServletRequest()));
        };
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
    @Qualifier("ssoUserDetailService")
    private UserDetailsService userDetailsService;

    /**
     * 手机号验证处理，当开启了手机号认证时，必须注入此 Bean
     *
     * @see com.hk.oauth2.server.service.impl.SmsPostAuthenticationHandler
     */
    @Autowired(required = false)
    @Qualifier(value = "smsPostAuthenticationHandler")
    private PostAuthenticationHandler<UserPrincipal, String> smsPostAuthenticationHandler;

    /**
     * @see com.hk.oauth2.server.service.impl.AlipayPostAuthenticationHandler
     */
    @Autowired(required = false)
    @Qualifier(value = "alipayPostAuthenticationHandler")
    private PostAuthenticationHandler<UserPrincipal, AlipayUserInfoShareResponse> alipayPostAuthenticationHandler;

    /**
     * 微信验证处理，当开启了微信认证时，必须注入此 Bean
     *
     * @see com.hk.oauth2.server.service.impl.WechatPostAuthenticationHandler
     */
    @Autowired(required = false)
    @Qualifier(value = "wechatPostAuthenticationHandler")
    private PostAuthenticationHandler<UserPrincipal, WxMpUser> wechatPostAuthenticationHandler;

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

    /**
     * 配置手机号登陆处理
     *
     * @param http http
     * @throws Exception Exception
     */
    private void configureSms(HttpSecurity http) throws Exception {
        AuthenticationProperties.SMSProperties sms = authenticationProperties.getSms();
        if (sms.isEnabled()) {
            SmsAuthenticationSecurityConfigurer configuration = new SmsAuthenticationSecurityConfigurer(sms, validateCodeProcessor,
                    smsPostAuthenticationHandler);
            if (StringUtils.isNotEmpty(sms.getClientId()) && StringUtils.isNotEmpty(sms.getClientSecret())) {
                configuration.setAuthenticationSuccessHandler(new PhoneAuthenticationSuccessHandler(sms.getClientId(),
                        sms.getClientSecret(),
                        applicationContext.getBean(AuthorizationServerTokenServices.class),
                        applicationContext.getBean(ClientDetailsService.class),
                        passwordEncoder));
            }
            configuration.setAuthenticationFailureHandler(new LoginAuthenticationFailureHandler(authenticationProperties.getLogin().getFailureUrl()));
            http.apply(configuration);
        }
    }

    /**
     * 配置微信登陆 处理
     *
     * @param http http
     * @throws Exception Exception
     */
    private void configureWeChat(HttpSecurity http) throws Exception {
        if (weiXinMpProperties.isEnabled()) {
            if (null == wxMpService) {
                throw new NullPointerException("wechat is enabled ,But wxMpService is null.");
            }
            if (null == wechatPostAuthenticationHandler) {
                throw new NullPointerException("wechat is enabled ,But wechatUserPrincipalService is null");
            }
            http.apply(new WeiXinAuthenticationSecurityConfigurer(wxMpService, weiXinMpProperties.getAuthentication(),
                    wechatPostAuthenticationHandler));
        }
    }

    /**
     * 配置支付宝登陆
     *
     * @param http httpSecurity
     */
    private void configureAipay(HttpSecurity http) throws Exception {
        if (alipayProperties.isEnabled()) {
            if (null == alipayClient) {
                throw new NullPointerException("alipay is enabled ,But alipayClient is null.");
            }
            http.apply(new AlipayAuthenticationSecurityConfigurer(alipayClient, alipayProperties.getCallbackUrl(),
                    alipayProperties.getState(), alipayPostAuthenticationHandler));
        }
    }

    @Bean
    public CreateSessionAuthenticationStrategy createSessionAuthenticationStrategy() {
        return new CreateSessionAuthenticationStrategy();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureSms(http);
        configureWeChat(http);
        configureAipay(http);
        AuthenticationProperties.LoginProperties login = authenticationProperties.getLogin();
        ConsumerTokenServices consumerTokenServices = applicationContext.getBean(ConsumerTokenServices.class);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests()
                .expressionHandler(new AdminAccessWebSecurityExpressionHandler());
        HttpSecurityUtils.buildPermitMatchers(urlRegistry, login.getPermitMatchers());
        http
                .csrf().disable()
                .formLogin()
                .loginPage(login.getLoginUrl()).permitAll() // 登陆 请求地址，不需要认证，否则会出现死循环
                .usernameParameter(login.getUsernameParameter())
                .passwordParameter(login.getPasswordParameter())
                .loginProcessingUrl(login.getLoginProcessingUrl())
                .failureUrl(login.getFailureUrl()) // 登陆失败地址，这个地址 Spring 会自动配置为不需要认证就可以访问
                .defaultSuccessUrl(login.getLoginSuccessUrl())
                .and()
                .rememberMe().disable()//禁用remember-me功能
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .enableSessionUrlRewriting(false)
                .sessionAuthenticationStrategy(createSessionAuthenticationStrategy())
                .maximumSessions(login.getMaximumSessions())
                .sessionRegistry(sessionRegistry())
                .expiredUrl(login.getSessionInvalidUrl())
                .maxSessionsPreventsLogin(login.isMaxSessionsPreventsLogin())
                .and()
                .and()
                .logout()
                .clearAuthentication(true)
                .logoutUrl(login.getLogoutUrl())
                .invalidateHttpSession(true)
                .addLogoutHandler(new SingleLogoutHandler(new DefaultSingleLogoutServiceMessageHandler(httpClient, tokenRegistry, consumerTokenServices)))
                .addLogoutHandler(new EquipmentLogoutHandler(login.getLogoutSuccessUrl()))
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


                // admin 角色的用户、admin权限、保护的用户拥有所有访问权限
//                @see https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
                .authorizeRequests().requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("admin") //访问所有以 /actuator/**的需要有admin 角色
                .anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**",
                "/email/register/**",// 邮箱号注册
                "/error", // 错误页面
                "/actuator/health",  // 健康检查
                "/wechat/login", // 微信二维码登陆
                StaticResourceLocation.FAVICON.name()); // ico
    }

    /**
     * 不将此 bean 注入到 Spring 上下文则不会有 没有 password grant_type
     *
     * @return AuthenticationManager
     * @throws Exception exception
     * @see Oauth2ServerAuthorizationServerConfigurer#configure(AuthorizationServerEndpointsConfigurer)
     * @see AuthorizationServerEndpointsConfigurer#getDefaultTokenGranters()
     */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
