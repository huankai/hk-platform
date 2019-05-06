package com.hk.oauth2.server.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpRequestFutureTask;
import org.springframework.beans.factory.DisposableBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author huangkai
 * @date 2019-05-06 22:34
 */
@Slf4j
@RequiredArgsConstructor
public class SimpleHttpClient implements HttpClient, Serializable, DisposableBean {

    /**
     * the request executor service for this client.
     */
    private final FutureRequestExecutionService requestExecutorService;

    @Override
    public boolean sendMessageToEndPoint(HttpMessage message) {
        try {
            final HttpPost request = new HttpPost(message.getUrl().toURI());
            request.addHeader(HttpHeaders.CONTENT_TYPE, message.getContentType());
            final StringEntity entity = new StringEntity(message.getMessage(), ContentType.create(message.getContentType()));
            request.setEntity(entity);
            final ResponseHandler<Boolean> handler = response -> response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
            log.debug("Created HTTP post message payload [{}]", request);
            final HttpRequestFutureTask<Boolean> task = this.requestExecutorService.execute(request, HttpClientContext.create(), handler);
            if (message.isAsynchronous()) {
                return true;
            }
            return task.get();
        } catch (final RejectedExecutionException e) {
            log.warn("Execution rejected", e);
            return false;
        } catch (final Exception e) {
            log.debug("Unable to send message", e);
            return false;
        }
    }

    @Override
    public void destroy() {
        try {
            if (requestExecutorService != null) {
                requestExecutorService.close();
            }
        } catch (final IOException e) {
            // ignore
        }
    }
}
