package com.hk.oauth2.server.http;

import com.hk.core.authentication.oauth2.configuration.ConfigurationKeys;
import org.springframework.http.MediaType;

import java.net.URL;

/**
 * logout httpMessage
 *
 * @author huangkai
 * @date 2019-05-06 22:04
 */
public class LogoutHttpMessage extends HttpMessage {

    public LogoutHttpMessage(URL url, String message) {
        this(url, message, true);
    }

    public LogoutHttpMessage(URL url, String message, boolean async) {
        super(url, message, async);
        setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    @Override
    public String getMessage() {
        return ConfigurationKeys.LOGOUT_PARAMETER_NAME.getName() + "=" + super.getMessage();
    }
}
