package com.hk.sso.server.config;

import com.hk.core.autoconfigure.authentication.security.oauth2.ResourceServerConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: kevin
 * @date 2018-07-16 11:06
 */
@Order(10)
@Configuration
@EnableResourceServer
public class SSOAuth2ResourceServerConfigurer extends ResourceServerConfigurer {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable();
    }
}
