package com.hk.oauth2.server.ticket;

/**
 * @author kevin
 * @date 2019-5-6 17:32
 */
public interface UniqueTicketIdGenerator {

    String getNewTicketId(String prefix);
}
