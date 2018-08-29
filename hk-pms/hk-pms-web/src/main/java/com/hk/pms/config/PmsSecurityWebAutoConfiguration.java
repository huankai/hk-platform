package com.hk.pms.config;

import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.oauth2.matcher.NoBearerMatcher;
import com.hk.core.authentication.security.savedrequest.GateWayHttpSessionRequestCache;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.core.autoconfigure.authentication.security.oauth2.OAuth2ClientAuthenticationConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


/**
 * @author: kevin
 * @date 2018-08-13 13:29
 */
@Order(1)
@Configuration
@EnableOAuth2Sso
public class PmsSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    private ApplicationContext applicationContext;

    /**
     * @see com.hk.core.autoconfigure.exception.DefaultErrorController#errorPath
     */
    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath;

    public PmsSecurityWebAutoConfiguration(AuthenticationProperties properties, ApplicationContext applicationContext) {
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
                .formLogin().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl(browser.getLogoutUrl())
                .logoutSuccessUrl(browser.getLogoutSuccessUrl())

                .and()
                .requestMatcher(NoBearerMatcher.INSTANCE)
                .authorizeRequests().anyRequest().authenticated();

        //通过源码分析，没有找到怎么个性化设置  OAuth2ClientAuthenticationProcessingFilter 对象一些参数值，所以这里注册一个
        http.apply(new OAuth2ClientAuthenticationConfigurer(oauth2SsoFilter(applicationContext.getBean(OAuth2SsoProperties.class))));

    }

    private OAuth2ClientAuthenticationProcessingFilter oauth2SsoFilter(OAuth2SsoProperties ssoProperties) {
        OAuth2RestOperations restTemplate = this.applicationContext.getBean(UserInfoRestTemplateFactory.class).getUserInfoRestTemplate();
        ResourceServerTokenServices tokenServices = this.applicationContext.getBean(ResourceServerTokenServices.class);
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(ssoProperties.getLoginPath());
        SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
        authenticationFailureHandler.setAllowSessionCreation(properties.isAllowSessionCreation());
        authenticationFailureHandler.setDefaultFailureUrl(properties.getDefaultFailureUrl());
        authenticationFailureHandler.setUseForward(properties.isForwardToDestination());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setRestTemplate(restTemplate);
        filter.setTokenServices(tokenServices);
        filter.setApplicationEventPublisher(this.applicationContext);
        return filter;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/favicon.ico", errorPath, properties.getDefaultFailureUrl());
    }
}
