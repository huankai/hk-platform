package com.hk.oauth2.server.web.support;

import com.hk.commons.cipher.CipherExecutor;
import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.web.Webs;
import com.hk.core.web.cookie.CookieProperties;
import com.hk.core.web.cookie.EncryptedCookieValueManager;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author huangkai
 * @date 2019-05-08 23:03
 */
@Slf4j
public class DefaultOauth2CookieValueManager extends EncryptedCookieValueManager {

    private static final char COOKIE_FIELD_SEPARATOR = '@';

    private static final int COOKIE_FIELDS_LENGTH = 3;

    private CookieProperties cookieProperties;

    public DefaultOauth2CookieValueManager(CipherExecutor<Serializable, Serializable> cipherExecutor,
                                           CookieProperties cookieProperties) {
        super(cipherExecutor);
        this.cookieProperties = cookieProperties;
    }

    @Override
    protected String buildCompoundCookieValue(String cookieValue, HttpServletRequest request) {
        final StringBuilder builder = new StringBuilder(cookieValue);
        if (cookieProperties.isPinToSession()) {
            builder.append(COOKIE_FIELD_SEPARATOR).append(Webs.getRemoteAddr(request));

            final String userAgent = Webs.getUserAgent(request);
            if (StringUtils.isEmpty(userAgent)) {
                throw new IllegalStateException("Request does not specify a user-agent");
            }
            builder.append(COOKIE_FIELD_SEPARATOR).append(userAgent);
        } else {
            log.debug("Cookie session-pinning is disabled");
        }
        return builder.toString();
    }

    @Override
    protected String obtainValueFromCompoundCookie(String cookieValue, HttpServletRequest request) {
        String[] cookieParts = StringUtils.tokenizeToStringArray(cookieValue, String.valueOf(COOKIE_FIELD_SEPARATOR));
        if (ArrayUtils.isEmpty(cookieParts)) {
            throw new IllegalStateException("Invalid empty cookie");
        }
        final String value = cookieParts[0];
        if (!cookieProperties.isPinToSession()) {
            log.debug("Cookie session-pinning is disabled. Returning cookie value as it was provided");
            return value;
        }
        if (cookieParts.length != COOKIE_FIELDS_LENGTH) {
            throw new IllegalStateException("Invalid cookie. Required fields are missing");
        }
        final String remoteAddr = cookieParts[1];
        final String userAgent = cookieParts[2];

        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(remoteAddr) || StringUtils.isEmpty(userAgent)) {
            throw new IllegalStateException("Invalid cookie. Required fields are empty");
        }
        String addr = Webs.getRemoteAddr(request);
        if (StringUtils.notEquals(remoteAddr, addr)) {
            throw new IllegalStateException("Invalid cookie. Required remote address "
                    + remoteAddr + " does not match " + addr);
        }
        final String agent = Webs.getUserAgent(request);
        if (StringUtils.notEquals(agent, userAgent)) {
            throw new IllegalStateException("Invalid cookie. Required user-agent " + userAgent + " does not match " + agent);
        }
        return value;
    }
}
