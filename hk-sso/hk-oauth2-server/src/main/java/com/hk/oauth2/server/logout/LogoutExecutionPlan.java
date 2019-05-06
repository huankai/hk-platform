package com.hk.oauth2.server.logout;


import com.hk.oauth2.server.http.LogoutHttpMessage;

import java.util.Collection;

/**
 * @author huangkai
 * @date 2019-05-06 22:08
 */
public interface LogoutExecutionPlan {

    /**
     * Register logout handler.
     *
     * @param handler the handler
     */
    void registerLogoutMessage(final LogoutHttpMessage message);

    /**
     * Gets logout handlers.
     *
     * @return the logout handlers
     */
    Collection<LogoutHttpMessage> getLogoutMessage();

}
