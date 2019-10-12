package com.hk.mail.controller;

import com.hk.commons.JsonResult;
import com.hk.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2019-01-08 17:46
 */
@RestController
@RequestMapping("mail")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * 发送邮件
     *
     * @param message message
     * @return {@link JsonResult}
     */
    @PostMapping(path = {"asyncSend"})
    public JsonResult<Void> asyncSend(@RequestBody SimpleMailMessage message) {
        emailService.asyncSend(message);
        return JsonResult.success();
    }
}
