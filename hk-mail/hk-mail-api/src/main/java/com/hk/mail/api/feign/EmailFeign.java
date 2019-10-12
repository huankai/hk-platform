package com.hk.mail.api.feign;

import com.hk.mail.api.domain.EmailMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangkai
 * @date 2019-01-08 17:32
 */
@FeignClient(name = EmailService.SERVICE_NAME, path = EmailService.CONTEXT_PATH)
@RequestMapping(path = {"api/email"})
public interface EmailFeign {

    /**
     * 发送邮件
     *
     * @param message 消息参数
     */
    @PostMapping(path = {"asyncSend"})
    void asyncSend(@RequestBody EmailMessage message);
}
