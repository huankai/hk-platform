package com.hk.oauth2.server.http;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;

import java.io.Serializable;
import java.net.URL;

/**
 * @author huangkai
 * @date 2019-05-06 21:24
 */
@Getter
@Setter
public class HttpMessage implements Serializable {

    private static final boolean DEFAULT_ASYNCHRONOUS_CALLBACKS_ENABLED = true;

    private final URL url;

    private final String message;

    private final boolean asynchronous;

    private String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;

    public HttpMessage(final URL url, final String message) {
        this(url, message, DEFAULT_ASYNCHRONOUS_CALLBACKS_ENABLED);
    }

    public HttpMessage(final URL url, final String message, final boolean async) {
        this.url = url;
        this.message = message;
        this.asynchronous = async;
    }
}
