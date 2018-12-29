package com.hk.app.rest;

import com.hk.commons.http.post.SimplePostHttpExecutor;
import com.hk.commons.util.CollectionUtils;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangkai
 * @date 2018-12-29 16:56
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    /**
     * 登陆
     *
     * @return login view
     */
    @GetMapping(path = "${hk.authentication.browser.login-url:/login}")
    public String login() {
        if (isAuthenticated()) {
            return "forward:/";
        }
        return "login";
    }

    /**
     * 登陆，使用密码模式
     *
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping(path = "${hk.authentication.browser.login-url:/login}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password) throws IOException {
        String clientId = oAuth2ProtectedResourceDetails.getClientId();
        String accessTokenUri = oAuth2ProtectedResourceDetails.getAccessTokenUri();
        String clientSecret = oAuth2ProtectedResourceDetails.getClientSecret();
        Map<String, Object> params = new HashMap<>();
        params.put(OAuth2Utils.CLIENT_ID, clientId);
        params.put("client_secret", clientSecret);
        params.put("username", username);
        params.put("password", password);
        params.put(OAuth2Utils.GRANT_TYPE, "password");
        return new SimplePostHttpExecutor().execute(accessTokenUri, params);
    }

    /**
     * <pre>
     * 刷新token:，需要如下参数:
     *  # grant_type的值固定为 refresh_token
     *  # refresh_token的值，每个验证成功的用户都会有 refresh_token值，需要根据此值来更新token
     *  # client_id
     *  # client_secret
     *  # scope: 可选
     *
     *  请求如下：
     * http://127.0.0.1:7100/oauth2/oauth/token
     *      ?grant_type=refresh_token
     *      &refresh_token=refresh_token_value
     *      &client_id=client_id
     *      &client_secret=client_secret
     *      &scope=scope
     * </pre>
     *
     * @param refreshToken refreshToken
     * @throws IOException
     */
    @PostMapping(path = "/refreshToken", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public String refreshToken(@RequestParam String refreshToken) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put(OAuth2Utils.CLIENT_ID, oAuth2ProtectedResourceDetails.getClientId());
        params.put("client_secret", oAuth2ProtectedResourceDetails.getClientSecret());
        params.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
        List<String> scopeList = oAuth2ProtectedResourceDetails.getScope();
        if (CollectionUtils.isNotEmpty(scopeList)) {
            params.put(OAuth2Utils.SCOPE, scopeList);
        }
        params.put(OAuth2AccessToken.REFRESH_TOKEN, refreshToken);
        return new SimplePostHttpExecutor().execute(oAuth2ProtectedResourceDetails.getAccessTokenUri(),
                params);
    }

}
