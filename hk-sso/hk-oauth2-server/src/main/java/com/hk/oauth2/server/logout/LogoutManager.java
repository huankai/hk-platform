package com.hk.oauth2.server.logout;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author kevin
 * @date 2019-4-27 12:38
 */
public interface LogoutManager {

    List<LogoutRequest> getLogoutRequest(HttpServletRequest request);

    void save(HttpServletRequest request, LogoutRequest logoutRequest);
}
