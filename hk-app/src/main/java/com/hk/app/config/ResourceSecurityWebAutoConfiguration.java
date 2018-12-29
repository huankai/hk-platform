package com.hk.app.config;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.authentication.security.expression.AdminAccessWebSecurityExpressionHandler;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.Set;

/**
 * @author kevin
 * @date 2018-08-17 13:24
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(AuthenticationProperties.class)
public class ResourceSecurityWebAutoConfiguration extends ResourceServerConfigurerAdapter {

    private AuthenticationProperties properties;

    public ResourceSecurityWebAutoConfiguration(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
//        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
//        authenticationEntryPoint.setExceptionRenderer((responseEntity, webRequest) -> {
//            if (null != webRequest && webRequest.getResponse() != null) {
//                Webs.writeJson(webRequest.getResponse(), HttpServletResponse.SC_UNAUTHORIZED,
//                        JsonResult.unauthorized(SpringContextHolder.getMessage("operation.unauthorized")));
//            }
//        });
//        resources.authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.BrowserProperties browser = properties.getBrowser();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http.authorizeRequests()
                .expressionHandler(new AdminAccessWebSecurityExpressionHandler());// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
//                .withObjectPostProcessor(new ObjectPostProcessor<AbstractSecurityExpressionHandler>() {
//                    @Override
//                    public <O extends AbstractSecurityExpressionHandler> O postProcess(O object) {
//                        return (O) new AdminAccessWebSecurityExpressionHandler();// admin 角色的用户、admin权限、保护的用户拥有所有访问权限
//                    }
//                });
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
        urlRegistry.antMatchers("/login").permitAll()
                .anyRequest().authenticated();
    }
}
