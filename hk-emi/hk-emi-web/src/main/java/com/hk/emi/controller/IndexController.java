package com.hk.emi.controller;

import com.hk.core.web.JsonResult;
import com.hk.platform.commons.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kevin
 * @date: 2018-08-14 16:19
 */
@Api(description = "首页管理")
@RestController
public class IndexController extends BaseController {

    @ApiOperation(value = "首页")
    @GetMapping({"/", "/index"})
    public JsonResult index() {
        return JsonResult.success(getPrincipal());
    }
}
