package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.emi.api.feign.CityFeignClient;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2018-08-14 16:19
 */
@RestController
public class IndexController extends BaseController {


    @GetMapping({"/", "/index"})
    public JsonResult<UserPrincipal> index() {
        LOGGER.info("Test...");
        LOGGER.info(JsonUtils.serialize(cityFeignClient.getChildList("0")));
        return JsonResult.success(SecurityContextUtils.getPrincipal());
    }

    @Autowired
    private CityFeignClient cityFeignClient;


}
