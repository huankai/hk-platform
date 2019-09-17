package com.hk.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Oauth2 Server Start
 *
 * @author kevin
 * @date 2018-07-13 11:50
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = {"com.hk.mail"})
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

}
