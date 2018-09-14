package com.hk.pms.controller;

import com.hk.commons.util.JsonUtils;
import com.hk.core.web.JsonResult;
import com.hk.emi.api.domain.City;
import com.hk.emi.api.feign.CityFeignClient;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试
 *
 * @author: kevin
 * @date: 2018-07-30 12:53
 */
@RestController
@Deprecated
public class SysTestController extends BaseController {

    @Autowired
    private CityFeignClient cityFeignClient;

    /**
     * 用户是否有 admin权限
     *
     * @return JsonResult
     */
    @GetMapping("permission-test")
    @PreAuthorize("hasAuthority('admin')")
    public JsonResult hasAuthorityAdmin() {
        // 调用 emi 服务
        List<City> provinceList = cityFeignClient.getChildList("4028c08162be57660162be5779cb0000");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("调用 EMI 服务：{}", JsonUtils.serialize(provinceList, true));
        }
        return JsonResult.success();
    }

    /**
     * 用户是否有 admin 角色
     *
     * @return JsonResult
     */
    @GetMapping("role-test")
    @PreAuthorize("hasRole('admin')")
    public JsonResult hasRoleAdmin() {
        return JsonResult.success();
    }

}
