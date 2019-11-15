package com.hk.oauth2.server;

import com.hk.core.data.jpa.query.specification.AggregateExpression;
import com.hk.core.data.jpa.query.specification.Criteria;
import com.hk.core.data.jpa.query.specification.Projections;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;

/**
 * Oauth2 Server Start
 *
 * @author kevin
 * @date 2018-07-13 11:50
 */
@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
@Slf4j
//@EnableFeignClients(basePackages = {"com.hk.mail"})
public class Oauth2ServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

    private final Oauth2ClientDetailsService oauth2ClientDetailsService;

    @Override
    public void run(String... args) throws Exception {
        Criteria<Oauth2ClientDetails> criteria = new Criteria<>();
        criteria.add(new AggregateExpression(Projections.max("id")));
        List<Oauth2ClientDetails> result = oauth2ClientDetailsService.findAll(criteria);
        log.debug("result:{}", result);
    }
}
