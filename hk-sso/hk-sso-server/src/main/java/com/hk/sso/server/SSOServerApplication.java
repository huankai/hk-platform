package com.hk.sso.server;


import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.IDGenerator;
import com.hk.sso.server.entity.SysApp;
import com.hk.sso.server.entity.SysUser;
import com.hk.sso.server.service.SysAppService;
import com.hk.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * SSO Start
 *
 * @author: kevin
 * @date: 2018-07-13 11:50
 */
@SpringBootApplication
@EnableEurekaClient
public class SSOServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSOServerApplication.class, args);
    }


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysAppService appService;

    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    /**
     * 初始化用户
     */
    @Component
    public class InitDbCommandLineRunner implements CommandLineRunner {

        @Override
        public void run(String... args) {
            if (userService.count() == 0) {
                List<SysUser> users = new ArrayList<>();
                SysUser user;
                List<String> accounts = ArrayUtils.asArrayList("18820136090", "18820132014");
                for (String account : accounts) {
                    user = new SysUser();
                    user.setOrgId("402881e662ba5fff0162ba602bff0000");
                    user.setDeptId("4028c08162bda84d0162bda85d6b0000");
                    user.setAccount(account);
                    user.setPhone(account);
                    user.setPassword(passwordEncoder.encode(account));
                    user.setEmail(account);
                    user.setRealName(account);
                    user.setUserType(ByteConstants.ZERO);
                    user.setIsProtect(true);
                    user.setSex(ByteConstants.ONE);
                    user.setUserStatus(ByteConstants.ONE);
                    users.add(user);
                }
                userService.batchInsert(users);
            }

            if (appService.count() == 0) {
                List<SysApp> sysApps = new ArrayList<>();
                List<String> appNameList = ArrayUtils.asArrayList("字典管理系统", "权限管理系统", "文件管理系统");
                List<String> appCodeList = ArrayUtils.asArrayList("HK-EMI", "HK-PMS", "HK-FS");
                SysApp app;
                int index = 0;
                for (String appName : appNameList) {
                    app = new SysApp();
                    app.setAppCode(appCodeList.get(index++));
                    app.setAppName(appName);
                    app.setAppHost("127.0.0.1");
                    app.setAppIcon(IDGenerator.STRING_UUID.generate() + ".png");
                    app.setAppStatus(ByteConstants.ONE);
                    app.setStartDate(LocalDateTime.now());
                    app.setLocalApp(true);
                    sysApps.add(app);
                }
                Iterable<SysApp> result = appService.batchInsert(sysApps);
                for (SysApp sysApp : result) {
                    BaseClientDetails details = new BaseClientDetails();
                    details.setClientId(sysApp.getId());
                    details.setClientSecret("{noop}" + sysApp.getId());
                    details.setScope(ArrayUtils.asArrayList("all"));
                    details.setAuthorizedGrantTypes(ArrayUtils.asArrayList("authorization_code", "refresh_token"));
                    details.setAccessTokenValiditySeconds(7200);
                    details.setRefreshTokenValiditySeconds(72000);
                    details.setAutoApproveScopes(ArrayUtils.asArrayList("true"));
                    clientDetailsService.addClientDetails(details);
                }
            }
        }

    }

}
