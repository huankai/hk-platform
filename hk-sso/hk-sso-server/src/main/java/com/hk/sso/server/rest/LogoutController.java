package com.hk.sso.server.rest;

import com.hk.commons.util.JsonUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 退出
 *
 * @author: kevin
 * @date 2018-07-31 14:44
 */
@RestController
public class LogoutController {

    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/oauth/logout")
    public String logout(HttpServletRequest request) {
        String token;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(authorization) && StringUtils.startsWithIgnoreCase(authorization, "Bearer ")) {
            token = StringUtils.substringAfter(authorization, "Bearer ");
        } else {
            token = request.getParameter("access_token");
        }
        if (StringUtils.isNotEmpty(token)) {
            consumerTokenServices.revokeToken(token);
        }
        System.out.println("退出成功：" + token);
        return JsonUtils.serialize(JsonResult.success());
    }
}
