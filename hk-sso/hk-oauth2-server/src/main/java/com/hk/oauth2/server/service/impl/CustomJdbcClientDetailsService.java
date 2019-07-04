package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.oauth2.exception.Oauth2ClientStatusException;
import com.hk.oauth2.provider.ClientDetailsCheckService;
import com.hk.oauth2.server.service.Oauth2ClientDetailsService;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

/**
 * @author huangkai
 * @date 2019-06-16 08:26
 */
public class CustomJdbcClientDetailsService implements ClientDetailsCheckService {

    private Oauth2ClientDetailsService oauth2ClientDetailsService;

    public CustomJdbcClientDetailsService(Oauth2ClientDetailsService oauth2ClientDetailsService) {
        this.oauth2ClientDetailsService = oauth2ClientDetailsService;
    }

    @Override
    public boolean isEnabled(String clientId) throws Oauth2ClientStatusException {
        return ByteConstants.ONE.equals(oauth2ClientDetailsService.findById(Long.parseLong(clientId))
                .orElseThrow(() -> new OAuth2AccessDeniedException("client状态异常")).getAppStatus());
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
