package com.hk.sso.server.rest;

import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.web.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取当前登陆的用户信息
 *
 * @author: kevin
 * @date 2018-07-16 10:32
 */
@RestController
public class UserController extends BaseController {

    @RequestMapping(path = "/user")
    public UserPrincipal getUserPrincipal() {
        return getPrincipal();
    }
}
