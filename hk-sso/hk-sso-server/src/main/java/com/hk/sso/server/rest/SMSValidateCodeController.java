package com.hk.sso.server.rest;

import com.hk.core.authentication.api.validatecode.ValidateCodeProcessor;
import com.hk.core.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: kevin
 * @date 2018-08-06 16:05
 */
@RestController
public class SMSValidateCodeController {

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
    public JsonResult createSmsCode(HttpServletRequest request) throws Exception {
        if (null == validateCodeProcessor) {
            throw new UnsupportedOperationException("系统未开启短信验证码登陆,请与管理员联系!");
        }
        validateCodeProcessor.create(new ServletWebRequest(request));
        return JsonResult.success();
    }

}
