package com.hk.fs.api.feign.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangkai
 * @date 2019-4-1 15:04
 */
@Configuration
public class FileConfiguration {

    @Bean
    public Logger.Level fileFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
