package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.platform.commons.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2018-08-14 16:19
 */
@Api(value = "首页管理")
@RestController
public class IndexController extends BaseController {

    @ApiOperation(value = "首页")
    @GetMapping({"/", "/index"})
    public JsonResult<UserPrincipal> index() {
        return JsonResult.success(getPrincipal());
    }
}
