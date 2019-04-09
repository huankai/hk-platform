package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.emi.api.feign.CityFeignClient;
import com.hk.emi.api.response.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-04-09 22:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CityFeignClient cityFeignClient;

    @GetMapping
    public JsonResult<?> test() {
        List<CityResponse> areaList = cityFeignClient.getAreaList();
        return JsonResult.success(areaList);
    }
}
