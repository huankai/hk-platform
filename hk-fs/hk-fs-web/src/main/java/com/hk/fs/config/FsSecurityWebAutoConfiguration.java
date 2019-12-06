package com.hk.fs.config;

import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.PermitMatcher;
import com.hk.core.authentication.oauth2.matcher.NoPermitMatcher;
import com.hk.core.authentication.oauth2.session.Oauth2UrlLogoutSuccessHandler;
import com.hk.core.authentication.oauth2.session.SessionMappingStorage;
import com.hk.core.authentication.oauth2.session.SingleSignOutFilter;
import com.hk.core.authentication.oauth2.session.SingleSignOutHandler;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.authentication.security.savedrequest.GateWayHttpSessionRequestCache;
import com.hk.core.autoconfigure.authentication.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.HttpSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.logout.LogoutFilter;

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

    @Autowired
    private SessionMappingStorage sessionMappingStorage;

    public FsSecurityWebAutoConfiguration(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.LoginProperties login = properties.getLogin();
        http.addFilterBefore(new SingleSignOutFilter(new SingleSignOutHandler(login.getLogoutUrl(), sessionMappingStorage)), LogoutFilter.class);
        if (StringUtils.isNotEmpty(login.getGateWayHost())) {
            http.requestCache().requestCache(new GateWayHttpSessionRequestCache(login.getGateWayHost()));
        }
        http
                .csrf().disable()
                .formLogin().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl(login.getLogoutUrl())
                .logoutUrl(login.getLogoutUrl())
                .logoutSuccessHandler(new Oauth2UrlLogoutSuccessHandler(login.getLogoutSuccessUrl()))

                .and()
                .requestMatcher(new NoPermitMatcher(login.getPermitMatchers()));
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests()
                .expressionHandler(new AdminAccessWebSecurityExpressionHandler()) // admin 角色的用户、admin权限、保护的用户拥有所有访问权限
                /*.withObjectPostProcessor(new ObjectPostProcessor<AbstractSecurityExpressionHandler>() {
                    @Override
                    public <O extends AbstractSecurityExpressionHandler> O postProcess(O object) {
                        return (O) new AdminAccessWebSecurityExpressionHandler();// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
                    }
                })*/;
        Set<PermitMatcher> permitMatchers = login.getPermitMatchers();
        HttpSecurityUtils.buildPermitMatchers(urlRegistry, permitMatchers);
        urlRegistry.anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/favicon.ico", properties.getOauth2().getOauth2FailureUrl());
    }
}
