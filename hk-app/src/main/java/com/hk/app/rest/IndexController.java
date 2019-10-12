package com.hk.app.rest;

import com.hk.commons.JsonResult;
import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.emi.api.domain.City;
import com.hk.emi.api.feign.CityFeignClient;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kally
 * @date 2018年8月17日下午1:56:50
 */
@RestController
public class IndexController extends BaseController {

    @Autowired
    private CityFeignClient cityFeignClient;

    @GetMapping({"/", "/index"})
    public JsonResult<UserPrincipal> test() {
        List<City> childList = cityFeignClient.getChildList("0");
        System.out.println(JsonUtils.serialize(childList, true));
        return JsonResult.success(getPrincipal());
    }
}
