package com.hk.oauth2.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;

/**
 * @author kevin
 * @date 2018-08-20 08:44
 */
@SuppressWarnings("serial")
@JsonSerialize(using = Oauth2ExceptionSerializer.OAuth2UnsupportedGrantedExceptionJackson2Serializer.class)
public class Oauth2UnsupportedGrantTypeException extends UnsupportedGrantTypeException {

    public Oauth2UnsupportedGrantTypeException(String msg) {
        super(msg);
    }

    public Oauth2UnsupportedGrantTypeException(String msg, Throwable t) {
        super(msg, t);
    }

}
