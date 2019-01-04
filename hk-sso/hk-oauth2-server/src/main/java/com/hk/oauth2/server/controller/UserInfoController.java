package com.hk.oauth2.server.controller;

import com.hk.core.authentication.api.UserPrincipal;
import com.hk.platform.commons.web.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 *
 * @author kevin
 * @date 2018-08-21 13:31
 */
@RestController
public class UserInfoController extends BaseController {

    /**
     * 当前用户信息
     *
     * @return userPrincipal
     */
    @GetMapping(path = "user")
    public UserPrincipal userPrincipal() {
        return getPrincipal();
    }

}
