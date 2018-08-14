package com.hk.sso.server.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;

@RestController
public class UserController {

	@GetMapping("/user")
	public UserPrincipal userPrincipal() {
		return SecurityContextUtils.getPrincipal();
	}
}
