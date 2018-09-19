package com.hk.fs.controller;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.web.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kevin
 * @date: 2018-09-13 10:37
 */
@RestController
public class IndexController {

    @GetMapping(path = {"/", "/index"})
    public JsonResult<UserPrincipal> index() {
        return JsonResult.success(SecurityContextUtils.getPrincipal());
    }
}
