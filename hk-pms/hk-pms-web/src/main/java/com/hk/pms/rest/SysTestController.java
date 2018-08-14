package com.hk.pms.rest;

import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.web.JsonResult;
import com.hk.emi.api.domain.City;
import com.hk.emi.api.feign.CityFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-07-30 12:53
 */
@RestController
public class SysTestController {

    @Autowired
    private CityFeignClient cityFeignClient;

    @GetMapping("permission")
    @PostAuthorize("hasAuthority('admin')")
    public JsonResult hasAuthorityAdmin() {
        List<City> provinceList = cityFeignClient.getChildList("4028c08162be57660162be5779cb0000");
        System.out.println(JsonUtils.serialize(provinceList, true));
        return JsonResult.success();
    }

    @GetMapping("role")
    @PostAuthorize("hasRole('admin')")
    public JsonResult hasRoleAdmin() {
        return JsonResult.success();
    }

}
