package com.hk.fs.config;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.oauth2.matcher.NoBearerMatcher;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.authentication.security.savedrequest.GateWayHttpSessionRequestCache;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.oauth2.OAuth2ClientAuthenticationConfigurer;
import com.hk.platform.commons.role.RoleNamed;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.util.Set;

/**
 * <p>
 * Order 的值需要注意配置
 * </p>
 *
 * @author kevin
 * @date 2018-08-13 13:29
 */
@Order(1)
@Configuration
@EnableOAuth2Sso
public class FsSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    private ApplicationContext applicationContext;

    public FsSecurityWebAutoConfiguration(AuthenticationProperties properties, ApplicationContext applicationContext) {
        this.properties = properties;
        this.applicationContext = applicationContext;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.BrowserProperties browser = properties.getBrowser();
        if (StringUtils.isNotEmpty(browser.getGateWayHost())) {
            http.requestCache().requestCache(new GateWayHttpSessionRequestCache(browser.getGateWayHost()));
        }
        http
                .csrf().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl(browser.getLogoutUrl())
                .logoutSuccessUrl(browser.getLogoutSuccessUrl())

                .and()
                .requestMatcher(NoBearerMatcher.INSTANCE);
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests()
                .expressionHandler(new AdminAccessWebSecurityExpressionHandler()) // admin 角色的用户、admin权限、保护的用户拥有所有访问权限
                /*.withObjectPostProcessor(new ObjectPostProcessor<AbstractSecurityExpressionHandler>() {
                    @Override
                    public <O extends AbstractSecurityExpressionHandler> O postProcess(O object) {
                        return (O) new AdminAccessWebSecurityExpressionHandler();// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
                    }
                })*/;
        Set<AuthenticationProperties.PermitMatcher> permitAllMatchers = browser.getPermitAllMatchers();
        if (CollectionUtils.isNotEmpty(permitAllMatchers)) {
            for (AuthenticationProperties.PermitMatcher permitMatcher : permitAllMatchers) {
                if (ArrayUtils.isNotEmpty(permitMatcher.getPermissions())) {
                    urlRegistry.antMatchers(permitMatcher.getMethod(), permitMatcher.getUris()).hasAnyAuthority(permitMatcher.getPermissions());
                } else if (ArrayUtils.isNotEmpty(permitMatcher.getRoles())) {
                    urlRegistry.antMatchers(permitMatcher.getMethod(), permitMatcher.getUris()).hasAnyRole(permitMatcher.getRoles());
                } else {
                    urlRegistry.antMatchers(permitMatcher.getMethod(), permitMatcher.getUris()).permitAll();
                }
            }
        }
        urlRegistry.mvcMatchers("/swagger-resources/**", "/swagger-ui.html").hasRole(RoleNamed.ADMIN)
                .anyRequest().authenticated();

        //通过源码分析，没有找到怎么个性化设置  OAuth2ClientAuthenticationProcessingFilter 对象一些参数值，所以这里注册一个
        http.apply(new OAuth2ClientAuthenticationConfigurer(oauth2SsoFilter(applicationContext.getBean(OAuth2SsoProperties.class))));
    }

    private OAuth2ClientAuthenticationProcessingFilter oauth2SsoFilter(OAuth2SsoProperties ssoProperties) {
        OAuth2RestOperations restTemplate = applicationContext.getBean(UserInfoRestTemplateFactory.class).getUserInfoRestTemplate();
        ResourceServerTokenServices tokenServices = applicationContext.getBean(ResourceServerTokenServices.class);
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(ssoProperties.getLoginPath());
        SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
        authenticationFailureHandler.setAllowSessionCreation(properties.isAllowSessionCreation());
        authenticationFailureHandler.setDefaultFailureUrl(properties.getDefaultFailureUrl());
        authenticationFailureHandler.setUseForward(properties.isForwardToDestination());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setRestTemplate(restTemplate);
        filter.setTokenServices(tokenServices);
        filter.setApplicationEventPublisher(applicationContext);
        return filter;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/favicon.ico", properties.getDefaultFailureUrl());
    }
}
