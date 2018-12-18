package com.hk.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Oauth2 Server Start
 *
 * @author kevin
 * @date 2018-07-13 11:50
 */
@SpringBootApplication
@EnableEurekaClient
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }


}
