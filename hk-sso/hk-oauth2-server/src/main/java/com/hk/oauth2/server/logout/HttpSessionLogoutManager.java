package com.hk.oauth2.server.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kevin
 * @date 2019-4-27 12:40
 */
@SuppressWarnings("unchecked")
public class HttpSessionLogoutManager implements LogoutManager {

    private static final String SERVICE_MANAGER = "OAUTH2_SERVICE_MANAVER";

    @Override
    public List<LogoutRequest> getLogoutRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session) {
            return Collections.emptyList();
        }
        try {
            return (List<LogoutRequest>) session.getAttribute(SERVICE_MANAGER);
        } finally {
            session.removeAttribute(SERVICE_MANAGER);
        }
    }

    @Override
    public void save(HttpServletRequest request, LogoutRequest logoutRequest) {
        HttpSession session = request.getSession(false);
        if (null != session) {
            List<LogoutRequest> values = (List<LogoutRequest>) session.getAttribute(SERVICE_MANAGER);
            if (null == values) {
                values = new ArrayList<>();
            }
            values.add(logoutRequest);
            session.setAttribute(SERVICE_MANAGER, values);

        }
    }
}
