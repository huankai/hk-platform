package com.hk.pms.config;

import com.hk.business.validator.dict.FeignDictCodeServiceImpl;
import com.hk.commons.validator.DictService;
import com.hk.core.authentication.api.PermitMatcher;
import com.hk.core.authentication.oauth2.matcher.NoPermitMatcher;
import com.hk.core.authentication.oauth2.session.Oauth2UrlLogoutSuccessHandler;
import com.hk.core.authentication.oauth2.session.SessionMappingStorage;
import com.hk.core.authentication.oauth2.session.SingleSignOutFilter;
import com.hk.core.authentication.oauth2.session.SingleSignOutHandler;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.autoconfigure.authentication.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.HttpSecurityUtils;
import com.hk.core.autoconfigure.authentication.security.SecurityAuthenticationAutoConfiguration;
import com.hk.emi.api.feign.SysCodeFeignClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.savedrequest.RequestCache;

import java.util.Set;


/**
 * @author kevin
 * @date 2018-08-13 13:29
 */
@Order(1)
@Configuration
@EnableOAuth2Sso
public class PmsSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    private ApplicationContext applicationContext;

    /**
     *
     */
    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath;

    /**
     * @see SecurityAuthenticationAutoConfiguration#requestCache()
     */
    private RequestCache requestCache;

    @Autowired
    private SessionMappingStorage sessionMappingStorage;

    @Bean
    public DictService dictService(SysCodeFeignClient codeFeignClient) {
        return new FeignDictCodeServiceImpl(codeFeignClient);
    }

    public PmsSecurityWebAutoConfiguration(AuthenticationProperties properties, ObjectProvider<RequestCache> requestCaches,
                                           ApplicationContext applicationContext) {
        this.properties = properties;
        this.requestCache = requestCaches.getIfAvailable();
        this.applicationContext = applicationContext;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.LoginProperties login = properties.getLogin();
        OAuth2SsoProperties ssoProperties = applicationContext.getBean(OAuth2SsoProperties.class);
        // TODO 添加单点退出拦截器
        http.addFilterBefore(new SingleSignOutFilter(new SingleSignOutHandler(login.getLogoutUrl(), sessionMappingStorage)), LogoutFilter.class);
        if (null != requestCache) {//默认使用的是 HttpSessionRequestCache
            http.requestCache().requestCache(requestCache);
        }
        http
                .csrf().disable()
                .formLogin().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl(login.getLogoutUrl())
                .logoutSuccessHandler(new Oauth2UrlLogoutSuccessHandler(login.getLogoutSuccessUrl()))
                .and()
                .requestMatcher(new NoPermitMatcher(login.getPermitMatchers()));
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests()
                .expressionHandler(new AdminAccessWebSecurityExpressionHandler()); // admin 角色的用户、admin权限、保护的用户拥有所有访问权限

//                   也可以添加后置处理器来个性化设置， Spring Security 的配置在 ExpressionUrlAuthorizationConfigurer#getExpressionHandler
//                .withObjectPostProcessor(new ObjectPostProcessor<AbstractSecurityExpressionHandler>() {
//                    @Override
//                    public <O extends AbstractSecurityExpressionHandler> O postProcess(O object) {
//                        /**
//                         * admin 角色的用户、admin权限、保护的用户拥有所有访问权限
//                         * @see  WebExpressionVoter#expressionHandler
//                         * @see
//                         */
//                        return (O) new AdminAccessWebSecurityExpressionHandler();
//                    }
//                });
        Set<PermitMatcher> permitAllMatchers = login.getPermitMatchers();
        HttpSecurityUtils.buildPermitMatchers(urlRegistry, permitAllMatchers);
        urlRegistry.anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/**", "/actuator/health", "/favicon.ico", errorPath, properties.getOauth2().getOauth2FailureUrl());
    }
}
