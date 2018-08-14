package com.hk.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * FS Start
 *
 * @author: kevin
 * @date 2018-07-13 14:48
 */
@EnableOAuth2Sso
@SpringCloudApplication
public class FSApplication {

    public static void main(String[] args) {
        SpringApplication.run(FSApplication.class, args);
    }
}
