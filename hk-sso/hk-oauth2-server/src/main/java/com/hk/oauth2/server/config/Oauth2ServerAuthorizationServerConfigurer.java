package com.hk.oauth2.server.config;

import com.hk.commons.JsonResult;
import com.hk.core.authentication.oauth2.converter.LocalUserAuthenticationConverter;
import com.hk.core.authentication.oauth2.provider.token.store.redis.RedisTokenStore;
import com.hk.core.authentication.security.UserDetailClientService;
import com.hk.core.web.Webs;
import com.hk.oauth2.TokenRegistry;
import com.hk.oauth2.exception.Oauth2DefaultWebResponseExceptionTranslator;
import com.hk.oauth2.provider.code.RedisAuthorizationCodeServices;
import com.hk.oauth2.provider.token.ClientEquipmentAuthenticationKeyGenerator;
import com.hk.oauth2.provider.token.CustomTokenServices;
import com.hk.oauth2.server.enhancer.Oauth2JwtTokenEnhancer;
import com.hk.oauth2.server.service.SysAppService;
import com.hk.oauth2.server.service.impl.CustomJdbcClientDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2018-07-16 10:53
 * @see org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration
 */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class Oauth2ServerAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private AuthorizationServerProperties authorizationServerProperties;

    /**
     * 认证管理器
     */
    private AuthenticationManager authenticationManager;

    private DataSource dataSource;

    private PasswordEncoder passwordEncoder;

    private UserDetailClientService userDetailClientService;

    private SysAppService appService;

    private Oauth2JwtTokenEnhancer oauth2JwtTokenEnhancer;

    @Autowired
    private TokenRegistry tokenRegistry;

    public Oauth2ServerAuthorizationServerConfigurer(AuthorizationServerProperties authorizationServerProperties,
                                                     ObjectProvider<AuthenticationManager> authenticationManager,
                                                     UserDetailClientService userDetailClientService,
                                                     DataSource dataSource,
                                                     SysAppService appService,
                                                     Oauth2JwtTokenEnhancer oauth2JwtTokenEnhancer,
                                                     PasswordEncoder passwordEncoder) {
        this.authorizationServerProperties = authorizationServerProperties;
        this.authenticationManager = authenticationManager.getIfAvailable();
        this.userDetailClientService = userDetailClientService;
        this.dataSource = dataSource;
        this.appService = appService;
        this.oauth2JwtTokenEnhancer = oauth2JwtTokenEnhancer;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * redis 连接
     */
    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * @see ClientDetailsServiceConfiguration#clientDetailsService()
     * @return
     */
    @Bean
    @Primary
    public CustomJdbcClientDetailsService jdbcClientDetailsService() {
        return new CustomJdbcClientDetailsService(appService, dataSource);
    }

    /**
     * 配置Client 信息
     *
     * @param clients clients
     * @throws Exception Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder)
                //允许客户端使用 POST : http://localhost:8080/oauth/token?grant_type=password&client_id=client2&client_secret=secretClient2&username=18820132014&password=123456 方式获取 access_token
                .allowFormAuthenticationForClients()
                .addObjectPostProcessor(new ObjectPostProcessor<ClientCredentialsTokenEndpointFilter>() { //添加验证失败后的处理方式。

                    @Override
                    public <O extends ClientCredentialsTokenEndpointFilter> O postProcess(O endpointFilter) {
                        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
                        authenticationEntryPoint
                                .setExceptionRenderer((responseEntity, webRequest) -> {
                                    if (webRequest != null && webRequest.getResponse() != null) {
                                        Webs.writeJson(webRequest.getResponse(), HttpServletResponse.SC_UNAUTHORIZED, JsonResult.badRequest("认证失败！"));
                                    }
                                });
                        endpointFilter.setAuthenticationEntryPoint(authenticationEntryPoint);
                        return endpointFilter;
                    }
                });
        if (StringUtils.isNotEmpty(authorizationServerProperties.getTokenKeyAccess())) {
            security.tokenKeyAccess(authorizationServerProperties.getTokenKeyAccess());
        }
        if (StringUtils.isNotEmpty(authorizationServerProperties.getCheckTokenAccess())) {
            security.checkTokenAccess(authorizationServerProperties.getCheckTokenAccess());
        }
        if (StringUtils.isNotEmpty(authorizationServerProperties.getRealm())) {
            security.realm(authorizationServerProperties.getRealm());
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        JwtAccessTokenConverter jwtAccessTokenConverter = accessTokenConverter();
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(oauth2JwtTokenEnhancer); //注意顺序
        enhancers.add(jwtAccessTokenConverter);

        CustomTokenServices tokenServices = new CustomTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setTokenRegistry(tokenRegistry);
        /*
         *
         *  http://127.0.0.1:7100/oauth2/oauth/token?grant_type=refresh_token
         *  &client_secret=client_secret
         *  &client_id=client_id
         *  &refresh_token=refresh_token_value
         *
         * supportRefreshToken 与 reuseRefreshToken 不能相同
         * 如果 supportRefreshToken 配置配置为false 时，不支持 refresh_token，调用 refresh_token 抛出异常; @see  AppStatusTokenServices 中的 if (!supportRefreshToken)  逻辑
         * 如果 supportRefreshToken 配置为 true ,且 reuseRefreshToken 也配置为true 时，刷新 token 接口能调用成功，但生成的新的 refresh_token 不会替换老的 refresh_token，
         *      将不能使用 新的 refresh_token 再次刷新 token。 @see AppStatusTokenServices 中的  if (!reuseRefreshToken) 逻辑
         */
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(false);

        tokenServices.setClientDetailsCheckService(jdbcClientDetailsService());
        tokenServices.setAccessTokenEnhancer(tokenEnhancerChain);

        tokenEnhancerChain.setTokenEnhancers(enhancers);
        endpoints.authenticationManager(authenticationManager)
                .exceptionTranslator(new Oauth2DefaultWebResponseExceptionTranslator()) // 错误配置,如果要修改Oauth2认证错误信息，请重写此对象
                .accessTokenConverter(jwtAccessTokenConverter)
//                .reuseRefreshTokens(true) 这个是配置默认的 reuseRefreshToken，因为这里自己创建了，所以不需要设置了
                .tokenEnhancer(tokenEnhancerChain)
                .tokenServices(tokenServices)

                /* 使用Jdbc authorization 存储，需要 创建数据库表 oauth_code */
//              endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
                /* 使用 redis  */
                .authorizationCodeServices(new RedisAuthorizationCodeServices(connectionFactory))
                .tokenStore(tokenStore());
    }

    /**
     * 使用JWT
     *
     * @return TokenStore
     */
    private TokenStore tokenStore() {
        //使用 redis store
        RedisTokenStore tokenStore = new RedisTokenStore(connectionFactory);
        tokenStore.setAuthenticationKeyGenerator(new ClientEquipmentAuthenticationKeyGenerator());
        return tokenStore;
//        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 這個Bean 一定要注入
     * 如果不注入，spring oauth 不会注入 /oauth/token_key （org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint 这个端点）
     *
     * @return JwtAccessTokenConverter
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration.TokenKeyEndpointRegistrar
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(new LocalUserAuthenticationConverter(userDetailClientService));
        jwtAccessTokenConverter.setAccessTokenConverter(defaultAccessTokenConverter);
        jwtAccessTokenConverter.setSigningKey("abcdefg"); // 配置签名token,先随便写个值
        return jwtAccessTokenConverter;
    }

}
