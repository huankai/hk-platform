package com.hk.oauth2.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author huangkai
 * @date 2018-12-28 15:19
 */
@SuppressWarnings("serial")
@JsonSerialize(using = Oauth2ExceptionSerializer.Oauth2AppStatusExceptionJackson2Serializer.class)
public class Oauth2AppStatusException extends OAuth2Exception {

    public Oauth2AppStatusException(String msg) {
        super(msg);
    }

    public Oauth2AppStatusException(String msg, Throwable t) {
        super(msg, t);
    }
}
