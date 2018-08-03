package com.hk.pms.rest;

import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
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

}
