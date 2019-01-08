package com.hk.mail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;

/**
 * @author huangkai
 * @date 2019-01-08 17:13
 */
public interface EmailService {

    /**
     * 发送邮件
     *
     * @param mailMessage mailMessage
     */
    @Async
    void asyncSend(SimpleMailMessage mailMessage);
}
