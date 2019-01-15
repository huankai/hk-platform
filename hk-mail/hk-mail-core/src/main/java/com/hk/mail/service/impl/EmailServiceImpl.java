package com.hk.mail.service.impl;

import com.hk.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-01-08 17:16
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final MailProperties mailProperties;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, MailProperties mailProperties) {
        this.mailSender = mailSender;
        this.mailProperties = mailProperties;
    }

    @Override
    public void asyncSend(SimpleMailMessage mailMessage) {
        mailMessage.setFrom(mailProperties.getUsername());
        mailSender.send(mailMessage);
    }
}
