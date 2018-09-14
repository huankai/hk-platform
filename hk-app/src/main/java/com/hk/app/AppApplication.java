package com.hk.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: kevin
 * @date: 2018-08-06 17:57
 */
@EnableOAuth2Sso
@EnableFeignClients(basePackages = "com.hk")
@SpringCloudApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}
