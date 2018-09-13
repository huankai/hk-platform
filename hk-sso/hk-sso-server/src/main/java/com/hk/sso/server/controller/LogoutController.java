package com.hk.sso.server.controller;

import com.hk.commons.util.StringUtils;
import com.hk.core.web.JsonResult;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出
 *
 * @author: kevin
 * @date 2018-07-31 14:44
 */
@RestController
public class LogoutController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

//    @Autowired
//    @Qualifier("consumerTokenServices")
//    private ConsumerTokenServices consumerTokenServices;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    @RequestMapping("/oauth/logout")
    public JsonResult logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new SecurityContextLogoutHandler().logout(request, response, null);
//        String accessToken = extractToken(request);
//        if (null != accessToken) {
//            consumerTokenServices.revokeToken(accessToken);
//            SecurityContextHolder.clearContext();
//        }
        String redirectUrl = getRedirectUrl(request);
        if (StringUtils.isNotEmpty(redirectUrl)) {
            redirectStrategy.sendRedirect(request, response, redirectUrl);
            return null;
        } else {
            return JsonResult.success("退出成功");
        }
    }

    private String getRedirectUrl(HttpServletRequest request) {
        return request.getParameter("redirect_url");
    }


//    private String extractToken(HttpServletRequest request) {
//        // first check the header...
//        String token = extractHeaderToken(request);
//        // bearer type allows a request parameter as well
//        if (token == null) {
//            LOGGER.debug("Token not found in headers. Trying request parameters.");
//            token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
//            if (token == null) {
//                LOGGER.debug("Token not found in request parameters.  Not an OAuth2 request.");
//            }
//        }
//
//        return token;
//    }
//
//    /**
//     * Extract the OAuth bearer token from a header.
//     *
//     * @param request The request.
//     * @return The token, or null if no OAuth authorization header was supplied.
//     */
//    private String extractHeaderToken(HttpServletRequest request) {
//        Enumeration<String> headers = request.getHeaders("Authorization");
//        while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
//            String value = headers.nextElement();
//            if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
//                String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
//                int commaIndex = authHeaderValue.indexOf(',');
//                if (commaIndex > 0) {
//                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
//                }
//                return authHeaderValue;
//            }
//        }
//        return null;
//    }
}
