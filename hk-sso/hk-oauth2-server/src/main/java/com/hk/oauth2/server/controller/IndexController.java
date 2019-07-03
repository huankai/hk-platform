package com.hk.oauth2.server.controller;

import com.hk.commons.util.ByteConstants;
import com.hk.core.query.Order;
import com.hk.oauth2.server.entity.SysApp;
import com.hk.oauth2.server.service.SysAppService;
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
 * @author kevin
 * @date 2018-08-06 10:38
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private SysAppService appService;

    @GetMapping(path = {"/", "/index"})
    public String index(ModelMap modelMap) {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        modelMap.put("loginUserCount", allPrincipals.size());
        if (getPrincipal().isAdministrator()) {
            SysApp param = new SysApp();
            param.setAppStatus(ByteConstants.ONE);
            List<SysApp> appList = appService.findAll(param, Order.asc("local_app"),
                    Order.desc("start_date"), Order.asc("app_code"));
            modelMap.put("appList", appList);
        }
        return "index";
    }

}
