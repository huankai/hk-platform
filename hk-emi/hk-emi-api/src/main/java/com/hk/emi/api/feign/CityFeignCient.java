package com.hk.emi.api.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author: kevin
 * @date 2018-07-13 15:27
 */
@FeignClient(name = "hk-emi")
public interface CityFeignCient {


}
