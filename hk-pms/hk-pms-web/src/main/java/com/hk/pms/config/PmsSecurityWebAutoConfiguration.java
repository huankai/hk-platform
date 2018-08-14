package com.hk.pms.config;

import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * <p>
 * Order 注解配置值：如果配置了 {@link EnableAuthorizationServer } 注解，此值需要小于3,如果没有配置 这个注解 ，此值需要大于3
 * </p>
 *
 * @author: kevin
 * @date 2018-08-13 10:17
 */
@Order(1)
@Configuration
@EnableOAuth2Sso
public class PmsSecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

    private AuthenticationProperties properties;

    public PmsSecurityWebAutoConfiguration(AuthenticationProperties properties) {
        this.properties = properties;
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
                .authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/favicon.ico");
    }

}
