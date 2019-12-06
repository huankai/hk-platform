package com.hk.oauth2.server.config;

import com.hk.oauth2.AccessTokenRegistry;
import com.hk.oauth2.InvalidHttpSessionListener;
import com.hk.oauth2.TokenRegistry;
import com.hk.oauth2.http.HttpClient;
import com.hk.oauth2.http.SimpleHttpClientFactoryBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kevin
 * @date 2019-5-18 14:46
 */
@Configuration
public class Oauth2LogoutAutoConfiguration {

    @Bean(name = "noRedirectHttpClient")
    public HttpClient noRedirectHttpClient() {
        final SimpleHttpClientFactoryBean c = new SimpleHttpClientFactoryBean();
//        final HttpClientProperties httpClient = casProperties.getHttpClient();
//        c.setConnectionTimeout(Beans.newDuration(httpClient.getConnectionTimeout()).toMillis());
//        c.setReadTimeout((int) Beans.newDuration(httpClient.getReadTimeout()).toMillis());
        c.setRedirectsEnabled(false);
        c.setCircularRedirectsAllowed(false);
//        c.setSslSocketFactory(trustStoreSslSocketFactory());
        return c.getObject();
    }

    @Bean
    public TokenRegistry tokenRegistry() {
        return new AccessTokenRegistry();
    }

    @Bean
    public ServletListenerRegistrationBean<InvalidHttpSessionListener> singleSignOutHttpSessionListener(TokenRegistry tokenRegistry) {
        ServletListenerRegistrationBean<InvalidHttpSessionListener> registrationBean = new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new InvalidHttpSessionListener(tokenRegistry));
        return registrationBean;
    }


}
