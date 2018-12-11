package com.hk.config.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: haungkai
 * @date: 2018-12-06 17:08
 */
@Controller
public class IndexController {

	/**
	 * 
	 *  
	 * @return
	 */
	@RequestMapping(path = { "/", "/index" })
	public String index() {
		return "redirect:/actuator/health";
	}
}
