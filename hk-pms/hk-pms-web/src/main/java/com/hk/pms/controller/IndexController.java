package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.platform.commons.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2018-08-14 16:19
 */
@RestController
public class IndexController extends BaseController {

    @GetMapping({"/", "/index"})
    public JsonResult<UserPrincipal> index() {
        return JsonResult.success(getPrincipal());
    }
}
