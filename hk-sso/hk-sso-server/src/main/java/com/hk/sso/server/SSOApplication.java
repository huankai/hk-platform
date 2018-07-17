package com.hk.sso.server;


import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.api.AppCode;
import com.hk.core.authentication.api.AppCodeContext;
import com.hk.core.authentication.security.AbstractUserDetailService;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * SSO Start
 *
 * @author: kevin
 * @date 2018-07-13 11:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class, args);
    }

    @Bean
    public AbstractUserDetailService userDetailService() {
        return new AbstractUserDetailService() {
            @Override
            protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
                return new SecurityUserPrincipal(true, "4028c08162bda8ce0162bda8df6a0000",
                        "18820136090", "$2a$10$KgOArE6QpbY2iTQC0WGGS.hP72PQsHpToqbNVEEmUrd5LcEqrbzAG",
                        "admin", ByteConstants.ONE, "18820136090", "huankai@139.com",
                        ByteConstants.ZERO, null, ByteConstants.ONE);
            }
        };
    }

    @Bean
    public AppCodeContext appCodeContext() {
        return new AppCodeContext() {
            @Override
            public AppCode getCurrentAppInfo() {
                return null;
            }
        };
    }
}
