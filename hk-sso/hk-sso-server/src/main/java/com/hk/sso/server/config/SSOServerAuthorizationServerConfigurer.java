package com.hk.sso.server.config;

import com.hk.core.web.JsonResult;
import com.hk.core.web.Webs;
import com.hk.sso.server.enhancer.SSOJwtTokenEnhancer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: kevin
 * @date 2018-07-16 10:53
 * @see org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration
 */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(AuthorizationServerProperties.class)
public class SSOServerAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    private AuthorizationServerProperties authorizationServerProperties;

    /**
     * 认证管理器
     */
    private AuthenticationManager authenticationManager;

    private DataSource dataSource;

    private PasswordEncoder passwordEncoder;

    public SSOServerAuthorizationServerConfigurer(AuthorizationServerProperties authorizationServerProperties,
                                                  ObjectProvider<AuthenticationManager> authenticationManager,
                                                  DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.authorizationServerProperties = authorizationServerProperties;
        this.authenticationManager = authenticationManager.getIfAvailable();
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    // /**
    // * redis 连接
    // */
    // @Autowired
    // private RedisConnectionFactory connectionFactory;

    //
    // /**
    // * redis token 存储
    // *
    // * @return
    // */
    // @Bean
    // @ConditionalOnMissingBean(TokenStore.class)
    // public TokenStore tokenStore() {
    // return new RedisTokenStore(connectionFactory);
    // }
    //
    //

    /**
     * 配置Client 信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients() //允许客户端使用 POST : http://localhost:8080/oauth/token?grant_type=password&client_id=client2&client_secret=secretClient2&username=18820132014&password=123456 方式获取 access_token
                .addObjectPostProcessor(new ObjectPostProcessor<ClientCredentialsTokenEndpointFilter>() { //添加验证失败后的处理方式。

                    @Override
                    public <O extends ClientCredentialsTokenEndpointFilter> O postProcess(O object) {
                        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
                        authenticationEntryPoint
                                .setExceptionRenderer((responseEntity, webRequest) -> Webs.writeJson(webRequest.getResponse(), HttpServletResponse.SC_UNAUTHORIZED, JsonResult.badRueqest("认证失败！")));
                        object.setAuthenticationEntryPoint(authenticationEntryPoint);
                        return object;
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
        enhancers.add(ssoJwtTokenEnhancer); //注意 顺序
        enhancers.add(jwtAccessTokenConverter);

        tokenEnhancerChain.setTokenEnhancers(enhancers);
        endpoints.authenticationManager(authenticationManager)
                .exceptionTranslator(new DefaultWebResponseExceptionTranslator()) // 错误配置,如果要修改Oauth2认证错误信息，请重写些对象
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                .tokenStore(tokenStore());
    }

    /**
     * 使用JWT
     *
     * @return
     */
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Autowired
    private SSOJwtTokenEnhancer ssoJwtTokenEnhancer;

    /**
     * 這個Bean 一定要注解 ，
     * 如果不注入，spring oauth 不会注入 /oauth/token_key （org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint 这个端点）
     *
     * @return
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration.TokenKeyEndpointRegistrar
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("8d1f6ddf6ef341deb6ff654e93179d6c"); // 配置签名token,先随便写个值
        return jwtAccessTokenConverter;
    }

}
