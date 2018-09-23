package com.hk.geteway.zuul;

import com.hk.geteway.zuul.filters.WebsocketFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Zuul
 *
 * @author: kevin
 * @date: 2018-07-13 11:56
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public WebsocketFilter websocketFilter(){
        return new WebsocketFilter();
    }

//    @Bean
//    public AccessFilter accessFilter() {
//        return new AccessFilter();
//    }
}
