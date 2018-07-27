package com.hk.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: kevin
 * @date 2018-07-27 16:42
 */
@Controller
public class IndexController {

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}
