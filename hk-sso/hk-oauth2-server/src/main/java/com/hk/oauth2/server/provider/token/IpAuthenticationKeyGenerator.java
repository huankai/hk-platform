package com.hk.oauth2.server.provider.token;

import com.hk.core.web.Webs;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author huangkai
 * @date 2019-05-06 21:36
 */
public class IpAuthenticationKeyGenerator implements AuthenticationKeyGenerator {

    private static final String CLIENT_ID = "client_id";

    private static final String SCOPE = "scope";

    private static final String USERNAME = "username";

    private static final String IP = "ip";

    public String extractKey(OAuth2Authentication authentication) {
        Map<String, String> values = new LinkedHashMap<>();
        OAuth2Request authorizationRequest = authentication.getOAuth2Request();
        if (!authentication.isClientOnly()) {
            values.put(USERNAME, authentication.getName());
        }
        values.put(CLIENT_ID, authorizationRequest.getClientId());
        if (authorizationRequest.getScope() != null) {
            values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<>(authorizationRequest.getScope())));
        }
        HttpServletRequest request = Webs.getHttpServletRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            System.out.println("header:" + header + ",value:" + request.getHeader(header));
        }
        System.out.println(request.getRequestURI());
        values.put(IP, Webs.getRemoteAddr());// 添加ip
        return generateKey(values);
    }

    private String generateKey(Map<String, String> values) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(values.toString().getBytes(StandardCharsets.UTF_8));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", nsae);
        }
    }
}
