package com.hk.pms.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author: kevin
 * @date 2018-08-13 09:25
 */
//@Configuration
//@EnableResourceServer
public class ResourceSecurityWebAutoConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().anyRequest().authenticated();
    }
}
