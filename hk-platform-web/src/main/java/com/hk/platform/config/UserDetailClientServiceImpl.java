package com.hk.platform.config;

import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.pms.domain.SysUser;
import com.hk.pms.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * @author huangkai
 * @date 2019-01-18 11:13
 */
public class UserDetailClientServiceImpl implements UserDetailsService {

    private final SysUserService userService;

    public UserDetailClientServiceImpl(SysUserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findByLoginUsername(username).orElseThrow(() -> new UsernameNotFoundException("账号不存在:" + username));
        SecurityUserPrincipal userPrincipal = new SecurityUserPrincipal(user.getId(), user.getOrgId(), null, null, null, user.getAccount(), user.getRealName(),
                user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getPassword(), user.getUserStatus(), null, null);
//        userPrincipal.setOrgId(user.getOrgId());
//        userPrincipal.setDeptId(user.getDeptId());
//        userPrincipal.setOrgName(sysOrgService.getById(user.getOrgId()).getOrgName());
//        userPrincipal.setDeptName(orgDeptService.getById(user.getDeptId()).getDeptName());
        return userPrincipal;
    }
}
