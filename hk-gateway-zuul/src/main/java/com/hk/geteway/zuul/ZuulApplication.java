package com.hk.geteway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: kevin
 * @date 2018-07-13 11:56
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableOAuth2Sso
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

//    @Bean
//    public AccessFilter accessFilter() {
//        return new AccessFilter();
//    }
}
