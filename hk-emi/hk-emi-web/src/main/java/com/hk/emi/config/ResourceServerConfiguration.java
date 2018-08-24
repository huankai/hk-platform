package com.hk.emi.config;

import com.hk.core.web.JsonResult;
import com.hk.core.web.Webs;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: kevin
 * @date 2018-08-21 11:04
 */
//@Configuration
//@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint((request, response, exception) -> Webs.writeJson(response, HttpServletResponse.SC_UNAUTHORIZED, JsonResult.badRueqest("用户未认证！")))
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
