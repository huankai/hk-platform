package com.hk.app.config;

import com.hk.core.web.JsonResult;
import com.hk.core.web.Webs;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: kevin
 * @date 2018-08-17 13:24
 */
@Configuration
@EnableResourceServer
public class ResourceSecurityWebAutoConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionRenderer((responseEntity, webRequest) ->
                Webs.writeJson(webRequest.getResponse(), HttpServletResponse.SC_UNAUTHORIZED, JsonResult.badRueqest("用户未认证！")));
        resources.authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }
}
