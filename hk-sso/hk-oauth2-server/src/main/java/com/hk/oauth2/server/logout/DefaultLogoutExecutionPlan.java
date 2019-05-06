package com.hk.oauth2.server.logout;

import com.hk.oauth2.server.http.LogoutHttpMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangkai
 * @date 2019-05-06 22:10
 */
@Slf4j
public class DefaultLogoutExecutionPlan implements LogoutExecutionPlan {

    private Map<String,List<LogoutHttpMessage>> map = new HashMap<>();

    @Override
    public void registerLogoutMessage(LogoutHttpMessage message) {

    }

    @Override
    public Collection<LogoutHttpMessage> getLogoutMessage() {
        return null;
    }

//    @Override
//    public void registerLogoutHandler(LogoutHandler handler) {
//        log.debug("Registering logout handler [{}]", handler.getName());
//        logoutHandlers.add(handler);
//    }
//
//    @Override
//    public Collection<LogoutHandler> getLogoutHandlers() {
//        return logoutHandlers;
//    }
}
