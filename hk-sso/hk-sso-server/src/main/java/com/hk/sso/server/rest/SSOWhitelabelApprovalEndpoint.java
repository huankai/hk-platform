package com.hk.sso.server.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 替换默认的确认页，直接重定向到 /oauth/authorize
 *
 * @author: kevin
 * @date 2018-08-03 10:23
 * @see org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint
 */
//@RestController
//@SessionAttributes("authorizationRequest")
public class SSOWhitelabelApprovalEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSOWhitelabelApprovalEndpoint.class);

    @RequestMapping("/oauth/confirm_access")
    public void getAccessConfirmation(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.debug("model:{}", model);
        String url = ServletUriComponentsBuilder.fromContextPath(request).build().getPath() + "/oauth/authorize?user_oauth_approval=true";
        response.sendRedirect(url);
    }


}
