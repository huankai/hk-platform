package com.hk.config.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author haungkai
 * @date 2018-12-06 17:08
 */
@Controller
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * @return
     */
    @RequestMapping(path = {"/", "/index"})
    public String index() {
        logger.debug("logger...");
        return "redirect:/actuator/health";
    }
}
