package com.hk.oauth2.server.config;

import com.hk.oauth2.http.SimpleHttpClient;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.FactoryBean;

import javax.net.ssl.HostnameVerifier;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @date 2019-5-18 14:29
 */
@Setter
public class SimpleHttpClientFactoryBean implements FactoryBean<SimpleHttpClient> {

    public static final int MAX_CONNECTIONS_PER_ROUTE = 50;

    private static final int MAX_POOLED_CONNECTIONS = 100;

    private static final int DEFAULT_THREADS_NUMBER = 200;

    private static final int DEFAULT_TIMEOUT = 5000;

    private static final int DEFAULT_QUEUE_SIZE = (int) (DEFAULT_THREADS_NUMBER * 0.2);

    private int threadsNumber = DEFAULT_THREADS_NUMBER;

    private int queueSize = DEFAULT_QUEUE_SIZE;

    private int maxPooledConnections = MAX_POOLED_CONNECTIONS;

    private int maxConnectionsPerRoute = MAX_CONNECTIONS_PER_ROUTE;

    private long connectionTimeout = DEFAULT_TIMEOUT;

    private int readTimeout = DEFAULT_TIMEOUT;

    private RedirectStrategy redirectionStrategy = new DefaultRedirectStrategy();

    private SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();

    private HostnameVerifier hostnameVerifier = new DefaultHostnameVerifier();

    private CredentialsProvider credentialsProvider;

    private CookieStore cookieStore;

    private ConnectionReuseStrategy connectionReuseStrategy = new DefaultConnectionReuseStrategy();

    private ConnectionBackoffStrategy connectionBackoffStrategy = new DefaultBackoffStrategy();

    private ServiceUnavailableRetryStrategy serviceUnavailableRetryStrategy = new DefaultServiceUnavailableRetryStrategy();

    private Collection<? extends Header> defaultHeaders = new ArrayList<>(0);

    private AuthenticationStrategy proxyAuthenticationStrategy = new ProxyAuthenticationStrategy();

    private boolean circularRedirectsAllowed = true;

    private boolean authenticationEnabled;

    private boolean redirectsEnabled = true;

    private ExecutorService executorService;

    @Override
    public SimpleHttpClient getObject() {
        final CloseableHttpClient httpClient = buildHttpClient();
        final FutureRequestExecutionService requestExecutorService = buildRequestExecutorService(httpClient);
        return new SimpleHttpClient(requestExecutorService);
    }

    private FutureRequestExecutionService buildRequestExecutorService(final CloseableHttpClient httpClient) {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(this.threadsNumber, this.threadsNumber, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(this.queueSize));
        }
        return new FutureRequestExecutionService(httpClient, this.executorService);
    }

    @SneakyThrows
    private CloseableHttpClient buildHttpClient() {
        final ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        final LayeredConnectionSocketFactory sslsf = this.sslSocketFactory;
        final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf).register("https", sslsf).build();
        final PoolingHttpClientConnectionManager connMgmr = new PoolingHttpClientConnectionManager(registry);
        connMgmr.setMaxTotal(maxPooledConnections);
        connMgmr.setDefaultMaxPerRoute(this.maxConnectionsPerRoute);
        connMgmr.setValidateAfterInactivity(DEFAULT_TIMEOUT);
        final HttpHost httpHost = new HttpHost(InetAddress.getLocalHost());
        final HttpRoute httpRoute = new HttpRoute(httpHost);
        connMgmr.setMaxPerRoute(httpRoute, MAX_CONNECTIONS_PER_ROUTE);
        final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(this.readTimeout)
                .setConnectTimeout((int) connectionTimeout).setConnectionRequestTimeout((int) this.connectionTimeout)
                .setCircularRedirectsAllowed(circularRedirectsAllowed).setRedirectsEnabled(this.redirectsEnabled)
                .setAuthenticationEnabled(authenticationEnabled).build();
        final HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connMgmr)
                .setDefaultRequestConfig(requestConfig).setSSLSocketFactory(sslsf)
                .setSSLHostnameVerifier(hostnameVerifier).setRedirectStrategy(this.redirectionStrategy)
                .setDefaultCredentialsProvider(credentialsProvider).setDefaultCookieStore(this.cookieStore)
                .setConnectionReuseStrategy(connectionReuseStrategy)
                .setConnectionBackoffStrategy(connectionBackoffStrategy)
                .setServiceUnavailableRetryStrategy(serviceUnavailableRetryStrategy)
                .setProxyAuthenticationStrategy(this.proxyAuthenticationStrategy)
                .setDefaultHeaders(defaultHeaders).useSystemProperties();
        return builder.build();
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleHttpClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
