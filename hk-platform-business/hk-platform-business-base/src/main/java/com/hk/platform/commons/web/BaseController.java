package com.hk.platform.commons.web;

import com.hk.commons.util.SpringContextHolder;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: kevin
 * @date: 2018-6-1 21:00
 */
public abstract class BaseController {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityContext securityContext;

    protected final UserPrincipal getPrincipal() {
        return securityContext.getPrincipal();
    }

    /**
     * 获取国际化消息
     *
     * @param code code
     * @param args args
     * @return
     */
    protected final String getMessage(String code, Object... args) {
        return getMessage(code, null, args);
    }

    /**
     * 获取国际化消息
     *
     * @param code           code
     * @param defaultMessage defaultMessage
     * @param args           args
     * @return
     */
    protected final String getMessage(String code, String defaultMessage, Object... args) {
        return SpringContextHolder.getMessage(code, defaultMessage, args);
    }
}
