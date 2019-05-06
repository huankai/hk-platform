package com.hk.oauth2.server.logout;

import com.hk.commons.util.CompressionUtils;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author kevin
 * @date 2019-4-27 12:40
 */
@NoArgsConstructor
public class DefaultLogoutManager implements LogoutManager {

    @Setter
    private LogoutMessageCreator logoutMessageBuilder;

//    private static final String SERVICE_MANAGER = "OAUTH2_SERVICE_MANAVER";

    @Override
    public String createFrontChannelLogoutMessage(LogoutRequest logoutRequest) {
        String logoutMessage = logoutMessageBuilder.create(logoutRequest);
        return CompressionUtils.compress(logoutMessage);
    }

//    @Override
//    public List<LogoutRequest> getLogoutRequest(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (null == session) {
//            return Collections.emptyList();
//        }
//        try {
//            return (List<LogoutRequest>) session.getAttribute(SERVICE_MANAGER);
//        } finally {
//            session.removeAttribute(SERVICE_MANAGER);
//        }
//    }
//
//    @Override
//    public void save(HttpServletRequest request, LogoutRequest logoutRequest) {
//        HttpSession session = request.getSession(false);
//        if (null != session) {
//            List<LogoutRequest> values = (List<LogoutRequest>) session.getAttribute(SERVICE_MANAGER);
//            if (null == values) {
//                values = new ArrayList<>();
//            }
//            values.add(logoutRequest);
//            session.setAttribute(SERVICE_MANAGER, values);
//
//        }
//}
}
