package com.hk.pms.config;

import com.hk.business.validator.dict.FeignDictCodeServiceImpl;
import com.hk.commons.validator.DictService;
import com.hk.emi.api.feign.SysCodeFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kevin
 * @date 2018-09-19 12:35
 */
@Configuration
public class PmsAutoConfiguration {

//    /**
//     * 使用 postGre Database
//     *
//     * @return Dialect
//     */
//    @Bean
//    public Dialect dialect() {
//        return new PostgreSqlDialect();
//    }

    @Bean
    public DictService dictService(SysCodeFeignClient codeFeignClient) {
        return new FeignDictCodeServiceImpl(codeFeignClient);
    }
}
