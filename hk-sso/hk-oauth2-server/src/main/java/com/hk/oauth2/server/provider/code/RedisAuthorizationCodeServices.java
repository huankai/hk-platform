package com.hk.oauth2.server.provider.code;

import com.hk.commons.util.ArrayUtils;
import com.hk.core.web.Webs;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 使用Redis 存储 authorization_code 认证的 code 与 authentication
 *
 * @author huangkai
 * @date 2018-12-29 09:37
 * @see org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices //使用 Jdbc 存儲
 * @see org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices // 内存存储
 */
@Slf4j
@RequiredArgsConstructor
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    /**
     * 过期时间，30  秒
     */
    private static final long EXPIRE = 30;

    private static final String HOST = "client_host";

    private static final String USER_AGENT = "user_agent";

    @Setter
    private String prefix = RedisAuthorizationCodeServices.class.getSimpleName() + ".Code";

    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.openPipeline();
            /*
             * 获取用户请求的客户端信息
             * 因为默认 spring 会为每个 client　请求生成一个认证信息，并在用户退出时，没有将　access_token 从 缓存中删除
             * 如果此时用户修改了用户信息，再次登陆时，还是会从缓存中查询用户的认证信息
             * @see org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator spring 生成 认证信息 Key 的策略
             * @see com.hk.oauth2.server.provider.token.ClientEquipmentAuthenticationKeyGenerator 自定认生成 认证信息 Key 的策略
             *
             * */
            HttpServletRequest request = Webs.getHttpServletRequest();
            String remoteAddr = Webs.getRemoteAddr(request);
            String userAgent = Webs.getUserAgent(request);
            String key = obtainClientEquipmentKey(code);

            connection.setEx(code.getBytes(StandardCharsets.UTF_8), EXPIRE, SerializationUtils.serialize(authentication));
            connection.setEx(key.getBytes(StandardCharsets.UTF_8), EXPIRE, SerializationUtils.serialize(new ClientAuthorizationEquipment(remoteAddr, userAgent)));
            connection.closePipeline();
        } finally {
            connection.close();
        }
    }

    private String obtainClientEquipmentKey(String code) {
        return prefix + code;
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            byte[] clientEquipmentKey = obtainClientEquipmentKey(code).getBytes(StandardCharsets.UTF_8);
            byte[] codeBytes = code.getBytes(StandardCharsets.UTF_8);
            connection.openPipeline();
            connection.get(codeBytes);
            connection.get(clientEquipmentKey);
            connection.del(codeBytes);
            connection.del(clientEquipmentKey);

            List<Object> results = connection.closePipeline();
            byte[] codeResult = (byte[]) results.get(0);
            byte[] equipmentResult = (byte[]) results.get(1);
            if (ArrayUtils.isEmpty(codeResult) || ArrayUtils.isEmpty(equipmentResult)) {
                return null;
            }
            OAuth2Authentication deserialize = SerializationUtils.deserialize(codeResult);

            /*
             * 将客户端 使用的ip 与 user_agent 存放在 认证的扩展信息中，
             *
             * @see  com.hk.oauth2.server.provider.token.ClientEquipmentAuthenticationKeyGenerator#extractKey() 从扩展信息中生成 Key
             * */
            ClientAuthorizationEquipment authorizationEquipment = SerializationUtils.deserialize(equipmentResult);
            OAuth2Request oAuth2Request = deserialize.getOAuth2Request();
            Map<String, Serializable> extensions = oAuth2Request.getExtensions();
            extensions.put(HOST, authorizationEquipment.getRemoteAddr());
            extensions.put(USER_AGENT, authorizationEquipment.getUserAgent());
            return deserialize;
        } finally {
            connection.close();
        }
    }
}
