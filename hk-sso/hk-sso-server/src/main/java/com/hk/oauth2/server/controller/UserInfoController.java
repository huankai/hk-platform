package com.hk.sso.server.controller;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 *
 * @author: kevin
 * @date: 2018-08-21 13:31
 */
@RestController
public class UserInfoController {

    @Value("${spring.datasource.url}")
    private String value;

    /**
     * 当前用户信息
     *
     * @return userPrincipal
     */
    @GetMapping("/user")
    public UserPrincipal userPrincipal() {
        System.out.println("----------------------->>" + value);
        return SecurityContextUtils.getPrincipal();
    }
}
