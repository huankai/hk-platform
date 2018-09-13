package com.hk.sso.server.controller;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import org.springframework.beans.factory.annotation.Value;
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

    /**
     * @see AuthenticationProperties.SMSProperties#enabled
     */
    @Value("${hk.authentication.sms.enabled:false}")
    private boolean smsEnabled;

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
        modelMap.put("smsEnabled", smsEnabled);
        return "login";
    }

}
