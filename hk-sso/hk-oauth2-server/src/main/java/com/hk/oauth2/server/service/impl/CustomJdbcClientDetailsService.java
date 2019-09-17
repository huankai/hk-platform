package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.oauth2.exception.Oauth2ClientStatusException;
import com.hk.oauth2.provider.ClientDetailsCheckService;
import com.hk.oauth2.server.entity.Oauth2ClientDetails;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.time.LocalDate;

/**
 * @author huangkai
 * @date 2019-06-16 08:26
 */
@RequiredArgsConstructor
public class CustomJdbcClientDetailsService implements ClientDetailsCheckService {

    private final Oauth2ClientDetailsService oauth2ClientDetailsService;

    @Override
    public void check(String clientId) throws Oauth2ClientStatusException {
        Oauth2ClientDetails clientDetails = oauth2ClientDetailsService.findById(Long.parseLong(clientId))
                .orElseThrow(() -> new OAuth2AccessDeniedException("client状态异常"));
        if (clientDetails.getExpireDate() != null && LocalDate.now().isAfter(clientDetails.getExpireDate())) {
            throw new Oauth2ClientStatusException("当前客户端应用已过期，请与管理员联系");
        }
        if (!ByteConstants.ONE.equals(clientDetails.getAppStatus())) {
            throw new Oauth2ClientStatusException("当前客户端应用已禁用，请与管理员联系");
        }
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            long id = Long.parseLong(clientId);
            return oauth2ClientDetailsService.findById(id).orElseThrow(() -> new NoSuchClientException("client 不存在"));
        } catch (NumberFormatException e) {
            throw new NoSuchClientException("client 不存在");
        }
    }
}
