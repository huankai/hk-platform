package com.hk.pms;

import com.hk.core.authentication.api.AppCode;
import com.hk.core.authentication.api.AppCodeContext;
import com.hk.core.authentication.security.AbstractUserDetailService;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.pms.domain.SysApp;
import com.hk.pms.domain.SysUser;
import com.hk.pms.service.SysAppService;
import com.hk.pms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * PMS Start
 *
 * @author: kevin
 * @date 2018-07-13 14:20
 */
@SpringCloudApplication
public class PMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(PMSApplication.class, args);
    }

    @Autowired
    private SysAppService appService;


    @Autowired
    private Environment environment;

    @Bean
    public AppCodeContext appCodeContext() {
        return () -> {
            SysApp sysApp = appService.findByAppCode(environment.getRequiredProperty("spring.application.name"));
            return new AppCode(sysApp.getId(), sysApp.getAppCode(), sysApp.getAppName());
        };
    }

    @Autowired
    private SysUserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new AbstractUserDetailService() {
            @Override
            protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
                SysUser user = userService.findByLoginUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
                SecurityUserPrincipal principal = new SecurityUserPrincipal(user.getIsProtect(), user.getId(), user.getRealName(), user.getPassword()
                        , user.getRealName(), user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getUserStatus());
                principal.setAppCode(appCodeContext().getCurrentAppInfo());
                return principal;
            }
        };
    }
}
