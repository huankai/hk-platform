package com.hk.sso.server.config;

import com.hk.sso.server.enhancer.SSOJwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: kevin
 * @date 2018-07-16 10:53
 */
@Configuration
@EnableAuthorizationServer
public class SSOServerAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

//    /**
//     * redis 连接
//     */
//    @Autowired
//    private RedisConnectionFactory connectionFactory;

//
//    /**
//     * redis token 存储
//     *
//     * @return
//     */
//    @Bean
//    @ConditionalOnMissingBean(TokenStore.class)
//    public TokenStore tokenStore() {
//        return new RedisTokenStore(connectionFactory);
//    }
//
//
    @Autowired
    private DataSource dataSource;

    /**
     * 配置Client 信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
//        clients.jdbc(dataSource).clients(new JdbcClientDetailsService(dataSource));
//        clients.inMemory()
//                .withClient("client1")
//                .scopes("all")
//                .secret("{noop}secretClient1")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .and()
//                .withClient("client2")
//                .secret("{noop}secretClient2")
//                .scopes("all")
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token");
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()");//授权表达式,要访问tokenKey 时需要身份验证.
    }

    //
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        JwtAccessTokenConverter jwtAccessTokenConverter = accessTokenConverter();
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(ssoJwtTokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(enhancers);
        endpoints
                .authenticationManager(authenticationManager)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenEnhancer(tokenEnhancerChain)
                .tokenStore(tokenStore());
    }


    /**
     * 使用JWT
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(TokenStore.class)
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @ConditionalOnMissingBean(AccessTokenConverter.class)
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("ssoTest"); //配置签名token
        return jwtAccessTokenConverter;
    }

    @Autowired
    private SSOJwtTokenEnhancer ssoJwtTokenEnhancer;
}
