package com.hk.emi;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.security.SpringSecurityContext;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public SecurityContext securityContext() {
        return new SpringSecurityContext();
    }
}
