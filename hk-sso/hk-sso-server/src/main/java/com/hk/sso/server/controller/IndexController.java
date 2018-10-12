package com.hk.sso.server.controller;

import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页
 *
 * @author: kevin
 * @date: 2018-08-06 10:38
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping(path = {"/", "/index"})
    public String index(ModelMap modelMap) {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        modelMap.put("loginUserCount", allPrincipals.size());
        return "index";
    }


}
