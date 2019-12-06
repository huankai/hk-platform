package com.hk.oauth2.server.controller;

import com.hk.core.autoconfigure.authentication.AuthenticationProperties;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationProperties authenticationProperties;

    /**
     * 登陆
     *
     * @param modelMap modelMap
     * @return login view
     */
    @GetMapping(path = "login")
    public String login(ModelMap modelMap) {
//        if (isAuthenticated()) {
//            return "redirect:/";
//        }
        modelMap.put("smsEnabled", authenticationProperties.getSms().isEnabled());
        return "login";
    }
}
