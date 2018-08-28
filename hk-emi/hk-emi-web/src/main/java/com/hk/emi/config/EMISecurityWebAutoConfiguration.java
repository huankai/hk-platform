package com.hk.emi.config;

import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.oauth2.matcher.NoBearerMatcher;
import com.hk.core.authentication.security.savedrequest.GateWayHttpSessionRequestCache;
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

    public EMISecurityWebAutoConfiguration(AuthenticationProperties properties) {
        this.properties = properties;
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
                .requestMatcher(NoBearerMatcher.INSTANCE)
                .authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/favicon.ico", properties.getDefaultFailureUrl());
    }
}
