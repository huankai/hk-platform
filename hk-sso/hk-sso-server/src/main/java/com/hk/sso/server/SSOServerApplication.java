package com.hk.sso.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * SSO Start
 *
 * @author: kevin
 * @date 2018-07-13 11:50
 */
@SpringBootApplication
@EnableEurekaClient
public class SSOServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOServerApplication.class, args);
    }


}
