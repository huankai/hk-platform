package com.hk.app.rest;

import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.emi.api.domain.City;
import com.hk.emi.api.feign.CityFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.web.JsonResult;

import java.util.List;

/**
 * @author: kally
 * @date: 2018年8月17日下午1:56:50
 */
@RestController
public class TestController {

    @Autowired
    private CityFeignClient cityFeignClient;

    @GetMapping({"/", "/test"})
    public JsonResult<UserPrincipal> test() {
        List<City> childList = cityFeignClient.getChildList("4028c08162be57660162be5779cb0000");
        System.out.println(JsonUtils.serialize(childList, true));
        return JsonResult.success(SecurityContextUtils.getPrincipal());
    }
}
