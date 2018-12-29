package com.hk.app.rest;

import com.hk.commons.http.post.SimplePostHttpExecutor;
import com.hk.commons.util.JsonUtils;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
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
     * @param modelMap modelMap
     * @return login view
     */
    @GetMapping(path = "/login")
    public String login(ModelMap modelMap) {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public String Login(@RequestParam String username, @RequestParam String password) throws IOException {
        if (isAuthenticated()) {
            return JsonUtils.serialize(getPrincipal());
        }
        String clientId = oAuth2ProtectedResourceDetails.getClientId();
        String accessTokenUri = oAuth2ProtectedResourceDetails.getAccessTokenUri();
        String clientSecret = oAuth2ProtectedResourceDetails.getClientSecret();
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", "password");
        return new SimplePostHttpExecutor().execute(accessTokenUri, params);
    }

}
