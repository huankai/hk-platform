package com.hk.oauth2.server.logout;

import com.hk.oauth2.server.ticket.DefaultUniqueTicketIdGenerator;
import com.hk.oauth2.server.ticket.UniqueTicketIdGenerator;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.zip.Inflater;

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
    private static final String LOGOUT_REQUEST_TEMPLATE = "<oauth2:LogoutRequest <oauth2:SessionIndex>%s</oauth2:SessionIndex></oauth2:LogoutRequest>";

    @Override
    public String create(LogoutRequest request) {
        return String.format(LOGOUT_REQUEST_TEMPLATE, GENERATOR.getNewTicketId("LR"));
    }

//    public static void main(String[] args) {
//        String value = String.format(LOGOUT_REQUEST_TEMPLATE, GENERATOR.getNewTicketId("LR-"));
//        System.out.println(value);
//        String compress = CompressionUtils.compress(value);
//        System.out.println(compress);
//        value = unCompressLogoutMessage(compress);
//        System.out.println(value);
//    }

    private static String unCompressLogoutMessage(final String originalMessage) {
        final byte[] binaryMessage = DatatypeConverter.parseBase64Binary(originalMessage);
        Inflater deCompresser = null;
        try {
            // decompress the bytes
            deCompresser = new Inflater();
            deCompresser.setInput(binaryMessage);
            final byte[] result = new byte[binaryMessage.length * 10];
            final int resultLength = deCompresser.inflate(result);
            // decode the bytes into a String
            return new String(result, 0, resultLength, StandardCharsets.UTF_8);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (deCompresser != null) {
                deCompresser.end();
            }
        }
    }
}
