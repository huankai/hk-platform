package com.hk.config.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: sjq-278
 * @date: 2018-12-06 17:08
 */
@Controller
public class IndexController {

    @RequestMapping(path = {"/","/index"})
    public String index(){
        return "redirect:/actuator/health";
    }
}
