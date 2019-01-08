package com.hk.mail.service.impl;

import com.hk.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void asyncSend(SimpleMailMessage mailMessage) {
        mailSender.send(mailMessage);
    }
}
