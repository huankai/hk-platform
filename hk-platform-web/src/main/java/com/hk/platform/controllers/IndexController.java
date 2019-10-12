package com.hk.platform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author huangkai
 * @date 2018-12-25 16:51
 */
@Controller
public class IndexController {

    /**
     * 主页
     */
    @GetMapping(value = {"/", "/index"})
    public String login() {
        return "index";
    }

}
