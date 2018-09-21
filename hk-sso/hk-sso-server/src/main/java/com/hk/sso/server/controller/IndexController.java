package com.hk.sso.server.controller;

import com.hk.platform.commons.web.BaseController;
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
public class IndexController extends BaseController {

    @GetMapping(path = {"/", "/index"})
    public String index(ModelMap modelMap) {
        return "index";
    }


}
