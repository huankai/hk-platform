package com.hk.oauth2.server.controller;

import com.hk.core.autoconfigure.authentication.security.AuthenticationProperties;
import com.hk.oauth2.server.constants.ClientEquipment;
import com.hk.platform.commons.web.BaseController;
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
public class LoginController extends BaseController {

    @Autowired
    private AuthenticationProperties authenticationProperties;

    /**
     * 登陆
     *
     * @param modelMap modelMap
     * @return login view
     */
    @GetMapping(path = "login")
    public String login(ModelMap modelMap) {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        modelMap.put("smsEnabled", authenticationProperties.getSms().isEnabled());
        return "login";
    }

    /**
     * 手机端登陆页面
     *
     * @return
     */
    @GetMapping(path = ClientEquipment.PHONE + "/login")
    public String phoneLogin() {
        return "phoneLogin";
    }
}
