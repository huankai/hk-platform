package com.hk.oauth2.server.provider.token;

import com.hk.commons.util.Algorithm;
import com.hk.commons.util.CollectionUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 客户端认证设置 key　生成器
 *
 * @author huangkai
 * @date 2019-05-06 21:36
 * @see org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator
 * @see com.hk.oauth2.server.provider.code.RedisAuthorizationCodeServices#remove(String)
 */
public class ClientEquipmentAuthenticationKeyGenerator implements AuthenticationKeyGenerator {

    private static final String USERNAME = "username";

    public String extractKey(OAuth2Authentication authentication) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(USERNAME, authentication.getName());
        Map<String, Serializable> extensions = authentication.getOAuth2Request().getExtensions();
        if (CollectionUtils.isNotEmpty(extensions)) {
            values.putAll(extensions);
        }
        return generateKey(values);
    }

    protected String generateKey(Map<String, Object> values) {
        // 这里一定要排序， hashMap 无序，会导致生成的 key　不一样
        values = CollectionUtils.sortMapByKey(values);
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(Algorithm.MD5.getName());
            byte[] bytes = digest.digest(values.toString().getBytes(StandardCharsets.UTF_8));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (NoSuchAlgorithmException nsae) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).", nsae);
        }
    }
}
