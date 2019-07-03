package com.hk.oauth2.server.init;

import com.hk.commons.util.*;
import com.hk.oauth2.server.entity.SysApp;
import com.hk.oauth2.server.entity.SysOrg;
import com.hk.oauth2.server.entity.SysOrgDept;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.service.SysAppService;
import com.hk.oauth2.server.service.SysOrgDeptService;
import com.hk.oauth2.server.service.SysOrgService;
import com.hk.oauth2.server.service.UserService;
import com.hk.oauth2.server.service.impl.CustomJdbcClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 初始化
 *
 * @author huangkai
 * @date 2018-12-18 17:06
 */
@Component
@Order(value = 1)
public class InitDbCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysAppService appService;

    @Autowired
    private CustomJdbcClientDetailsService clientDetailsService;

    @Autowired
    private SysOrgService orgService;

    @Autowired
    private SysOrgDeptService orgDeptService;

    @Override
    public void run(String... args) {
        SysOrg sysOrg = getOrCreateSysOrg();
        SysOrgDept orgDept = getOrCreateSysDept(sysOrg.getId());
        createUser(orgDept.getOrgId(), orgDept.getId());
        createLocalApp();
    }

    private void createLocalApp() {
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
                details.setClientId(sysApp.getId().toString());
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

    private void createUser(Long orgId, Long deptId) {
        if (userService.count() == 0) {
            List<SysUser> users = new ArrayList<>();
            SysUser user;
            List<String> accounts = ArrayUtils.asArrayList("18820136090", "18820132014");
            for (String account : accounts) {
                user = new SysUser();
                user.setOrgId(orgId);
                user.setDeptId(deptId);
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
    }

    private SysOrgDept getOrCreateSysDept(Long orgId) {
        SysOrgDept param = new SysOrgDept();
        param.setOrgId(orgId);
        List<SysOrgDept> orgDeptResult = orgDeptService.findAll(param);
        Optional<SysOrgDept> optionalOrgDept = CollectionUtils.getFirstOrDefault(orgDeptResult);
        SysOrgDept orgDept;
        if (optionalOrgDept.isPresent()) {
            orgDept = optionalOrgDept.get();
        } else {
            orgDept = new SysOrgDept();
            orgDept.setOrgId(orgId);
            orgDept.setParentId(0L);
            orgDept.setDeptName("根机构部门");
            orgDept = orgDeptService.insert(orgDept);
        }
        return orgDept;
    }

    private SysOrg getOrCreateSysOrg() {
        SysOrg sysOrg = new SysOrg();
        sysOrg.setOrgCode("ADMIN");
        Optional<SysOrg> orgOptional = orgService.findOne(sysOrg);
        if (orgOptional.isPresent()) {
            sysOrg = orgOptional.get();
        } else {
            sysOrg.setParentId(Contants.DEFAULT_VALUE_LONG);
            sysOrg.setOrgName("根节点");
            sysOrg.setOrgIcon(IDGenerator.STRING_UUID.generate() + ".png");
            sysOrg.setResponsibleId(Contants.DEFAULT_VALUE_LONG);
            sysOrg.setParentId(Contants.DEFAULT_VALUE_LONG);
            sysOrg.setOrgTag(Contants.DEFAULT_VALUE);
            sysOrg.setProvinceId(Contants.DEFAULT_VALUE_LONG);
            sysOrg.setCityId(Contants.DEFAULT_VALUE_LONG);
            sysOrg.setAreaId(Contants.DEFAULT_VALUE_LONG);
            sysOrg.setAddress(Contants.DEFAULT_VALUE);
            sysOrg = orgService.insert(sysOrg);
        }
        return sysOrg;

    }
}
