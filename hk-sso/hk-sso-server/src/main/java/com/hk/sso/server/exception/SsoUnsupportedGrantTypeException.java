package com.hk.sso.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;

/**
 * @author: kevin
 * @date: 2018-08-20 08:44
 */
@JsonSerialize(using = SsoOAuth2ExceptionJackson2Serializer.class)
public class SsoUnsupportedGrantTypeException extends UnsupportedGrantTypeException {

    public SsoUnsupportedGrantTypeException(String msg) {
        super(msg);
    }

    public SsoUnsupportedGrantTypeException(String msg, Throwable t) {
        super(msg, t);
    }

}
