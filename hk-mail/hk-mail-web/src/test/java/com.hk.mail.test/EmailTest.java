package com.hk.mail.test;

import com.hk.core.test.BaseTest;
import com.hk.mail.MailApplication;
import com.hk.mail.service.EmailService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

/**
 * @author huangkai
 * @date 2019-01-08 15:34
 */
@SpringBootTest(classes = {MailApplication.class})
public class EmailTest extends BaseTest {

    @Autowired
    private EmailService emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTest.class);

    @Test
    public void asynSendTest() {
        LOGGER.info("Thread Name: ------------> {}", Thread.currentThread().getName());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huankai@139.com");
        message.setTo("852476317@qq.com");
        message.setSubject("tests");
        message.setSentDate(new Date());
        message.setText("测试sddf");
        emailService.asyncSend(message);

        /*
         使用异步发送邮件，主线程停止，发送邮件的线程可能还没有将邮件发送出去，会导致收不到，所以，在测试的时候等待邮件发送完再手动停止。
         */
        while (true) {

        }

    }
}
