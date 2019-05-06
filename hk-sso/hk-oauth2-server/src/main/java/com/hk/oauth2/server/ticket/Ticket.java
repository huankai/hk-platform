package com.hk.oauth2.server.ticket;

import java.io.Serializable;

/**
 * @author kevin
 * @date 2019-5-6 18:13
 */
public interface Ticket extends Serializable {

    String getId();

    String getPrefix();
}
