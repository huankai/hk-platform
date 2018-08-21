package com.hk.sso.server.rest;

import com.hk.core.authentication.api.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private Environment environment;

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
        // 是否开启短信登陆功能
        boolean smsEnabled = Boolean.valueOf(environment.getProperty("hk.authentication.sms.enabled", "false"));
        modelMap.put("smsEnabled", smsEnabled);
        return "login";
    }

}
