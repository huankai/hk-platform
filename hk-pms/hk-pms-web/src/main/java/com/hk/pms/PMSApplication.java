package com.hk.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * PMS Start
 *
 * @author kevin
 * @date 2018-07-13 14:20
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.hk")
//@EnableBinding(value = {Sink.class})
public class PMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(PMSApplication.class, args);
    }

}
