package com.hk.oauth2.server.logout;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author kevin
 * @date 2019-4-27 12:39
 */
public interface LogoutRequest extends Serializable {

    URI getLogoutUrl() throws URISyntaxException;

}
