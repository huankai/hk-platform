package com.hk.emi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * EMI Start
 *
 * @author: kevin
 * @date 2018-07-13 14:06
 */
@SpringCloudApplication
public class EMIApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMIApplication.class, args);
    }
}
