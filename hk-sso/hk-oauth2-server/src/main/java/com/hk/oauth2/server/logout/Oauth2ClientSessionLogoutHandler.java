package com.hk.oauth2.server.logout;

import com.hk.commons.util.CollectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author kevin
 * @date 2019-4-27 13:27
 */
@RequiredArgsConstructor
public class Oauth2ClientSessionLogoutHandler implements LogoutHandler {

    private final RestTemplate restTemplate;

    @Setter
    private LogoutManager logoutManager = new HttpSessionLogoutManager();

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        List<LogoutRequest> clientLogoutRequests = logoutManager.getLogoutRequest(request);
        if (CollectionUtils.isNotEmpty(clientLogoutRequests)) {
            clientLogoutRequests.forEach(item -> {
                try {
                    restTemplate.delete(item.getLogoutUrl());
                } catch (URISyntaxException e) {
                    // ingore
                }
            });
        }
    }
}
