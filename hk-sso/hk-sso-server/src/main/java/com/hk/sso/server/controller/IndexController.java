package com.hk.sso.server.controller;

import com.hk.core.authentication.api.SecurityContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 *
 * @author: kevin
 * @date: 2018-08-06 10:38
 */
@Controller
public class IndexController {

    @GetMapping(path = {"/", "/index"})
    public String index(ModelMap modelMap) {
        modelMap.put("user", SecurityContextUtils.getPrincipal());
        return "index";
    }


}
