package com.hk.pms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 配置这个的原因是feign使用 在每个微服务调用时也需要认证，
 * 会在请求头 (Authorization)
 * 或 请求参数(access_token)中带入认证信息
 * 如果不配置，feign将不能调用其它微服务
 *
 * @author kevin
 * @date 2018-08-21 11:04
 * @see com.hk.core.autoconfigure.authentication.security.oauth2.Oauth2FeignAutoConfiguration#oAuth2FeignRequestInterceptor
 */
@Configuration
@EnableResourceServer
public class ResourceSecurityWebAutoConfiguration extends ResourceServerConfigurerAdapter {

}
