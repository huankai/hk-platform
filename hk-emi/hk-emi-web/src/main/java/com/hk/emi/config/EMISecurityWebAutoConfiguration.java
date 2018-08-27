package com.hk.emi.config;

import com.hk.core.authentication.oauth2.matcher.NoBearerMatcher;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>
 * Order 的值需要注意配置
 * </p>
 *
 * @author: kevin
 * @date 2018-08-13 13:29
 */
@Order(1)
@Configuration
@EnableOAuth2Sso
public class EMISecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

//    private ApplicationContext applicationContext;

    public EMISecurityWebAutoConfiguration(AuthenticationProperties properties/*, ApplicationContext applicationContext*/) {
        this.properties = properties;
//        this.applicationContext = applicationContext;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationProperties.BrowserProperties browser = properties.getBrowser();
        http
                .csrf().disable()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl(browser.getLogoutUrl())
                .logoutSuccessUrl(browser.getLogoutSuccessUrl())

                .and()
                .requestMatcher(NoBearerMatcher.INSTANCE)
//                .antMatcher(ssoProperties.getLoginPath())
                .authorizeRequests().anyRequest().authenticated();
    }


//    @Bean
//    @Primary
//    public OAuth2ClientAuthenticationProcessingFilter oauth2SsoFilter(OAuth2SsoProperties ssoProperties) {
//        OAuth2RestOperations restTemplate = this.applicationContext.getBean(UserInfoRestTemplateFactory.class).getUserInfoRestTemplate();
//        ResourceServerTokenServices tokenServices = this.applicationContext.getBean(ResourceServerTokenServices.class);
//        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(ssoProperties.getLoginPath());
//        SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
//        authenticationFailureHandler.setAllowSessionCreation(properties.isAllowSessionCreation());
//        authenticationFailureHandler.setDefaultFailureUrl(properties.getDefaultFailureUrl());
//        authenticationFailureHandler.setUseForward(properties.isForwardToDestination());
//        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        filter.setRestTemplate(restTemplate);
//        filter.setTokenServices(tokenServices);
//        filter.setApplicationEventPublisher(this.applicationContext);
//        return filter;
//    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/favicon.ico", properties.getDefaultFailureUrl());
    }
}
