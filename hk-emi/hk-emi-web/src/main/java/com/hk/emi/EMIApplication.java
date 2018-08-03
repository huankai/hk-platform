package com.hk.emi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * EMI Start
 *
 * @author: kevin
 * @date 2018-07-13 14:06
 */
@EnableOAuth2Sso
@SpringCloudApplication
public class EMIApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMIApplication.class, args);
    }
}
