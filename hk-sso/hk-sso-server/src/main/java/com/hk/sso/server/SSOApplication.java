package com.hk.sso.server;


import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.security.AbstractUserDetailService;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.SpringSecurityContext;
import com.hk.sso.server.entity.SysUser;
import com.hk.sso.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * SSO Start
 *
 * @author: kevin
 * @date 2018-07-13 11:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SSOApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityContext securityContext() {
        return new SpringSecurityContext();
    }

    @Bean
    public AbstractUserDetailService userDetailService() {
        return new AbstractUserDetailService() {

            @Override
            protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
                SysUser user = userRepository.findByPhone(username).orElseThrow(() -> new UsernameNotFoundException("用户不存在!"));
                return new SecurityUserPrincipal(user.getIsProtect(), user.getId(), user.getRealName(), user.getPassword(), user.getRealName(),
                        user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getUserStatus());
//                return new SecurityUserPrincipal(true, "4028c08162bda8ce0162bda8df6a0000",
//                        "18820136090", "$2a$10$KgOArE6QpbY2iTQC0WGGS.hP72PQsHpToqbNVEEmUrd5LcEqrbzAG",
//                        "admin", ByteConstants.ONE, "18820136090", "huankai@139.com",
//                        ByteConstants.ZERO, null, ByteConstants.ONE);
            }
        };
    }
//
//    @Bean
//    public AppCodeContext appCodeContext() {
//        return new AppCodeContext() {
//            @Override
//            public AppCode getCurrentAppInfo() {
//                return null;
//            }
//        };
//    }
}
