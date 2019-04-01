package com.hk.emi.api.feign.fallback;

import com.hk.emi.api.feign.CityFeignClient;
import com.hk.emi.api.response.CityResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author kevin
 * @date 2018-08-31 16:34
 */
@Component
public class CityFeignClientFallback implements CityFeignClient {

    @Override
    public List<CityResponse> getChildList(String parentId) {
        return Collections.emptyList();
    }

    @Override
    public List<CityResponse> getList(int level) {
        return Collections.emptyList();
    }
}
