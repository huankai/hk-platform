package com.hk.emi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 配置这个的原因是feign使用 在每个微服务调用时也需要认证，
 * 会在请求头 (Authorization)
 * 或 请求参数(access_token)中带入认证信息
 * 如果不配置，feign将不能调用其它微服务
 *
 * @author: kevin
 * @date 2018-08-21 11:04
 * @see com.hk.core.autoconfigure.authentication.security.oauth2.Oauth2FeignAutoConfiguration#oAuth2FeignRequestInterceptor
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration {

//    private AuthenticationProperties properties;
//
//    private ApplicationContext applicationContext;
//
//    public ResourceServerConfiguration(AuthenticationProperties properties, ApplicationContext applicationContext) {
//        this.properties = properties;
//        this.applicationContext = applicationContext;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        AuthenticationProperties.BrowserProperties browser = properties.getBrowser();
//
//        OAuth2RestOperations restTemplate = this.applicationContext
//                .getBean(UserInfoRestTemplateFactory.class).getUserInfoRestTemplate();
//        OAuth2SsoProperties ssoProperties = applicationContext.getBean(OAuth2SsoProperties.class);
//        ResourceServerTokenServices tokenServices = this.applicationContext.getBean(ResourceServerTokenServices.class);
//        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(ssoProperties.getLoginPath());
//        filter.setRestTemplate(restTemplate);
//        filter.setTokenServices(tokenServices);
//        filter.setApplicationEventPublisher(this.applicationContext);
//        http.addFilterBefore(filter, AbstractPreAuthenticatedProcessingFilter.class);
//        http
//                .csrf().disable()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutUrl(browser.getLogoutUrl())
//                .logoutSuccessUrl(browser.getLogoutSuccessUrl())
//
//                .and()
//                .authorizeRequests().antMatchers("/static/**", "/favicon.ico", properties.getDefaultFailureUrl()).permitAll()
//                .anyRequest().authenticated();
//    }

}
