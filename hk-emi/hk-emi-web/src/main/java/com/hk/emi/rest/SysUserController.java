package com.hk.emi.rest;

import com.hk.commons.util.SpringContextHolder;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kevin
 * @date 2018-07-30 12:53
 */
@RestController
public class SysUserController {

    @GetMapping("user")
    public UserPrincipal currentUser() {
        return SecurityContextUtils.getPrincipal();
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
