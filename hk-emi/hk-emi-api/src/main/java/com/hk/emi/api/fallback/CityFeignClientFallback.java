package com.hk.emi.api.fallback;

import com.hk.emi.api.domain.City;
import com.hk.emi.api.feign.CityFeignClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author: kevin
 * @date 2018-08-31 16:34
 */
@Component
public class CityFeignClientFallback implements CityFeignClient {

    @Override
    public List<City> getChildList(String parentId) {
        return Collections.emptyList();
    }

    @Override
    public List<City> getList(int level) {
        return Collections.emptyList();
    }
}
