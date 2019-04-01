package com.hk.mail.api.feign.fallback;

import com.hk.mail.api.feign.EmailFeign;
import com.hk.mail.api.request.EmailMessageRequest;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author huangkai
 * @date 2019-4-1 15:28
 */
@Component
public class EmailFallbackFactory implements FallbackFactory<EmailFeign> {

    @Override
    public EmailFeign create(Throwable cause) {
        return new EmailFeign() {
            @Override
            public void asyncSend(EmailMessageRequest messageRequest) {

            }
        };
    }
}
