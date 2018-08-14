package com.hk.emi.rest;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.web.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kevin
 * @date 2018-08-14 16:19
 */
@RestController
public class IndexController {

    @GetMapping({"/", "/index"})
    public JsonResult index() {
        return JsonResult.success(SecurityContextUtils.getPrincipal());
    }
}
