package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.oauth2.exception.Oauth2ClientStatusException;
import com.hk.oauth2.provider.ClientDetailsCheckService;
import com.hk.oauth2.server.service.SysAppService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author huangkai
 * @date 2019-06-16 08:26
 */
public class CustomJdbcClientDetailsService extends JdbcClientDetailsService implements ClientDetailsCheckService {

    private SysAppService appService;

    public CustomJdbcClientDetailsService(SysAppService appService, DataSource dataSource) {
        super(dataSource);
        this.appService = appService;
    }

    @Override
    public boolean isEnabled(String clinetId) throws Oauth2ClientStatusException {
        return appService.findById(clinetId)
                .orElseThrow(() -> new Oauth2ClientStatusException("client 不存在 ：" + clinetId))
                .getAppStatus() == ByteConstants.ONE;
    }
}
