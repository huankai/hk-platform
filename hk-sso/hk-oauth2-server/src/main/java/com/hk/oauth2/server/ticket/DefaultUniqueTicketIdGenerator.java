package com.hk.oauth2.server.ticket;

import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.StringUtils;

/**
 * @author kevin
 * @date 2019-5-6 17:33
 */
public class DefaultUniqueTicketIdGenerator implements UniqueTicketIdGenerator {

    @Override
    public String getNewTicketId(String prefix) {
        return StringUtils.defaultIfEmpty(prefix, StringUtils.EMPTY) + IDGenerator.STRING_UUID.generate();
    }
}
