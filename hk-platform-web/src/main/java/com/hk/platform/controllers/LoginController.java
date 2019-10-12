package com.hk.platform.controllers;

import com.hk.core.authentication.api.SecurityContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author huangkai
 * @date 2018-12-25 16:51
 */
@Controller
public class LoginController {

    /**
     * 登陆
     *
     * @return login view
     */
    @GetMapping("/login")
    public String login() {
        if (SecurityContextUtils.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

}
