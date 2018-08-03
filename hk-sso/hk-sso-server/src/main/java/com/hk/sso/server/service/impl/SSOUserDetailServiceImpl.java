package com.hk.sso.server.service.impl;

import com.hk.core.authentication.security.AbstractUserDetailService;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.sso.server.entity.SysUser;
import com.hk.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author: kevin
 * @date 2018-07-31 12:56
 */
@Component
public class SSOUserDetailServiceImpl extends AbstractUserDetailService {

    private final UserService userService;

    @Autowired
    public SSOUserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
        SysUser user = userService.findByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException("不存在的用户:" + username));
        return new SecurityUserPrincipal(user.getId(), user.getAccount(), user.getIsProtect(), user.getRealName(),
                user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getPassword(), user.getUserStatus());
    }
}
