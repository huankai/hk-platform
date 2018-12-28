package com.hk.oauth2.server.controller;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Login
 *
 * @author kally
 * @date 2018年8月6日上午9:28:03
 */
@Controller
public class LoginController {

    @Autowired
    private AuthenticationProperties authenticationProperties;

    /**
     * 登陆
     *
     * @param modelMap modelMap
     * @return login view
     */
    @GetMapping("/login")
    public String login(ModelMap modelMap) {
        if (SecurityContextUtils.isAuthenticated()) {
            return "redirect:/";
        }
        modelMap.put("smsEnabled", authenticationProperties.getSms().isEnabled());
           return "login";
    }


}
