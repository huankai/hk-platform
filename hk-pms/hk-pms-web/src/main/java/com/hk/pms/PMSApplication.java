package com.hk.pms;

import com.hk.stream.order.OrderInput;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * PMS Start
 *
 * @author kevin
 * @date 2018-07-13 14:20
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.hk")
@EnableBinding(value = {OrderInput.class})
public class PMSApplication {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ConnectionFactory connectionFactory;

    public static void main(String[] args) {
        SpringApplication.run(PMSApplication.class, args);
    }

}
