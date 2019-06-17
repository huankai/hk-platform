package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.api.PostAuthenticationHandler;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 手机号登陆
 *
 * @author huangkai
 * @date 2019-05-11 23:04
 */
@Service(value = "smsPostAuthenticationHandler")
public class SmsPostAuthenticationHandler implements PostAuthenticationHandler<UserPrincipal, String> {

    @Autowired
    private UserService userService;

    @Override
    public UserPrincipal handler(String phone) {
        Optional<SysUser> phoneUser = userService.findByPhone(phone);
        UserPrincipal principal = new UserPrincipal();
        SysUser user;
        if (!phoneUser.isPresent()) {
            user = new SysUser();
            user.setPhone(phone);
            user.setUserType(ByteConstants.ZERO);
            user = userService.registerUser(user);
        } else {
            user = phoneUser.get();
        }
        principal.setUserId(user.getId());
        principal.setPhone(phone);
        principal.setAccount(user.getAccount());
        principal.setSex(user.getSex());
        principal.setDeptId(user.getDeptId());
        principal.setProtectUser(user.getIsProtect());
        principal.setIconPath(user.getIconPath());
        principal.setOrgId(user.getOrgId());
        principal.setUserType(user.getUserType());
        principal.setRealName(user.getRealName());
        principal.setEmail(user.getEmail());
        return principal;
    }
}
