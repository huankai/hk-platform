package com.hk.oauth2.server.provider.code;

import com.hk.commons.util.ArrayUtils;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

/**
 * 使用Redis 存储 authorization_code 认证的 code 与 authentication
 *
 * @author huangkai
 * @date 2018-12-29 09:37
 * @see org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices //使用 Jdbc 存儲
 * @see org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices // 内存存储
 */
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisAuthorizationCodeServices(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            //保存 60 秒
            connection.setEx(code.getBytes(), 60, SerializationUtils.serialize(authentication));
        } finally {
            connection.close();
        }
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            byte[] bytes = connection.get(code.getBytes());
            return ArrayUtils.isEmpty(bytes) ? null : SerializationUtils.deserialize(bytes);
        } finally {
            connection.close();
        }
    }
}
