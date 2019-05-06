package com.hk.oauth2.server.logout;

import com.hk.oauth2.server.ticket.DefaultUniqueTicketIdGenerator;
import com.hk.oauth2.server.ticket.UniqueTicketIdGenerator;

/**
 * @author kevin
 * @date 2019-5-6 17:36
 */
public class Oauth2LogoutMessageCreator implements LogoutMessageCreator {

    /**
     * A ticket Id generator.
     */
    private static final UniqueTicketIdGenerator GENERATOR = new DefaultUniqueTicketIdGenerator();

    /**
     * The logout request template.
     */
    private static final String LOGOUT_REQUEST_TEMPLATE = "<samlp:LogoutRequest xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\"><samlp:SessionIndex>%s</samlp:SessionIndex></samlp:LogoutRequest>";

    @Override
    public String create(LogoutRequest request) {
        return String.format(LOGOUT_REQUEST_TEMPLATE, GENERATOR.getNewTicketId("LR"));
    }
}
