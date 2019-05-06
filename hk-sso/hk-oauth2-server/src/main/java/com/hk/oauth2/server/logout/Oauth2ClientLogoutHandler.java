package com.hk.oauth2.server.logout;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kevin
 * @date 2019-4-27 13:27
 */
@RequiredArgsConstructor
public class Oauth2ClientLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        List<LogoutRequest> clientLogoutRequests = logoutManager.getLogoutRequest(request);
//        if (CollectionUtils.isNotEmpty(clientLogoutRequests)) {
//            clientLogoutRequests.forEach(item -> {
//                try {
//                    restTemplate.delete(item.getLogoutUrl());
//                } catch (URISyntaxException e) {
//                    // ingore
//                }
//            });

    }
}
