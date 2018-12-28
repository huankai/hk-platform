package com.hk.oauth2.server.controller;

import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 短信验证码
 *
 * @author kevin
 * @date 2018-08-06 16:05
 */
@RestController
public class SMSValidateCodeController extends BaseController {

    @Autowired(required = false)
    @Qualifier("smsValidateCodeProcessor")
    private ValidateCodeProcessor validateCodeProcessor;

    /**
     * 短信验证码发送
     *
     * @param request request
     * @throws Exception
     */
    @GetMapping("/sms/sender")
    public JsonResult<String> createSmsCode(HttpServletRequest request) throws Exception {
        if (null == validateCodeProcessor) {
            throw new UnsupportedOperationException(getMessage("sms.disable.message"));
        }
        String code = validateCodeProcessor.create(new ServletWebRequest(request));
        return new JsonResult<>(code);
    }

}
