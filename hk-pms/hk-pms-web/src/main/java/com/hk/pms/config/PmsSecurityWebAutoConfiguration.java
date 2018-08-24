package com.hk.pms.config;

import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
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
 * @date 2018-08-13 10:17
 */
@Order(1)
@Configuration
@EnableOAuth2Sso
public class PmsSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    private ApplicationContext applicationContext;

    public PmsSecurityWebAutoConfiguration(AuthenticationProperties properties, ApplicationContext applicationContext) {
        this.properties = properties;
        this.applicationContext = applicationContext;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.BrowserProperties browser = properties.getBrowser();
        http
//                .apply(new OAuth2ClientAuthenticationConfigurer(oauth2SsoFilter(applicationContext.getBean(OAuth2SsoProperties.class))))
//
//                .and()

                .csrf().disable()
                .formLogin().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl(browser.getLogoutUrl())
                .logoutSuccessUrl(browser.getLogoutSuccessUrl())

                .and().authorizeRequests().anyRequest().authenticated();
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
        web.ignoring().antMatchers("/static/**", "/favicon.ico", properties.getDefaultFailureUrl());
    }

}
