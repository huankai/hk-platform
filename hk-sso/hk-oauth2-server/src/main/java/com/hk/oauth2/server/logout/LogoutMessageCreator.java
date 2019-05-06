package com.hk.oauth2.server.logout;

/**
 * @author kevin
 * @date 2019-5-6 17:35
 */
public interface LogoutMessageCreator {

    String create(LogoutRequest request);
}
