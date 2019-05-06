package com.hk.oauth2.server.http;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author huangkai
 * @date 2019-05-06 22:33
 */
public interface HttpClient {

    boolean sendMessageToEndPoint(HttpMessage message);

    default boolean isValidEndPoint(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
