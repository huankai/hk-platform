package com.hk.pms.config;

import com.hk.core.data.jdbc.dialect.Dialect;
import com.hk.core.data.jdbc.dialect.PostgreSqlDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: kevin
 * @date: 2018-09-19 12:35
 */
@Configuration
public class PmsAutoConfiguration {

    /**
     * 使用 postGre Database
     *
     * @return Dialect
     */
    @Bean
    public Dialect dialect() {
        return new PostgreSqlDialect();
    }
}
