package com.hk.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangkai
 * @date 2019-01-18 09:14
 */
@SpringBootApplication(scanBasePackages = {"com.hk.platform", "com.hk.pms", "com.hk.emi"})
@MapperScan(basePackages = {"com.hk.pms.mappers", "com.hk.emi.mappers"})
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}
