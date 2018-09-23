package com.hk.emi.controller;

import com.hk.platform.commons.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: kevin
 * @date: 2018-08-14 16:19
 */
@Api(description = "首页管理")
@Controller
public class IndexController extends BaseController {

//    @ApiOperation(value = "首页")
//    @GetMapping({"/", "/index"})
//    public JsonResult<UserPrincipal> index() {
//        return JsonResult.success(getPrincipal());
//    }


    @ApiOperation(value = "首页")
    @GetMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        return "index";
    }
}
