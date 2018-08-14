package com.hk.emi.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;

/**
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
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/api/**").permitAll().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**", "/favicon.ico");
    }

}
