package com.hk.sso.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author: kevin
 * @date 2018-08-13 09:25
 */
@Configuration
@EnableResourceServer
public class ResourceSecurityWebAutoConfiguration extends ResourceServerConfigurerAdapter {

}
