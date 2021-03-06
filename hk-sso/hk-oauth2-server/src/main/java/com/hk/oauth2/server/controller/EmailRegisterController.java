package com.hk.oauth2.server.controller;

import com.hk.commons.util.AssertUtils;
import com.hk.oauth2.server.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 邮箱账号注册
 *
 * @author huangkai
 * @date 2019-01-09 14:19
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("email/register")
public class EmailRegisterController /*extends BaseController*/ {

    private final RegisterService registerService;

    private final ConsumerTokenServices consumerTokenServices;

    private final ClientDetailsService clientDetailsService;

    /**
     * @return register view 1
     */
    @GetMapping(path = "register-1")
    public String resister() {
        consumerTokenServices.revokeToken("a");
        ClientDetails result = clientDetailsService.loadClientByClientId("aadf");
        return "register/email/register_1";
    }

    /**
     * @param email        邮箱号
     * @param password     密码
     * @param password2    确认密码
     * @param validateCode 验证码
     * @return register view 2
     */
    @PostMapping(path = "register-2")
    public String register2(@RequestParam String email, @RequestParam String password, @RequestParam String password2,
                            @RequestParam String validateCode) {
        AssertUtils.notEmpty(validateCode);
        registerService.emailRegister(email, password, password2);
        return "register/email/register_2";
    }

    /**
     * 完善用户资料信息
     *
     * @param id 生成唯一验证码id，这里指定 用户id
     * @return register view 3
     */
    @GetMapping(path = "register-3/{id}")
    public String register3(@PathVariable Long id, ModelMap modelMap) {
        registerService.checkEmailValidate(id);
        modelMap.put("id", id);
        return "register/email/register_3";
    }

    /**
     * 完成注册
     *
     * @param id       注册用户id
     * @param realName 用户真实名
     * @param sex      用户性别
     * @param birthday 生日
     * @param iconPath 头像地址
     * @return redirect 到首页
     */
    @PostMapping(path = "complete")
    public String completeRegister(@RequestParam Long id, @RequestParam String realName, Byte sex, LocalDate birthday, String iconPath) {
        registerService.completeRegister(id, realName, sex, birthday, iconPath);
        return "redirect:/";
    }

}
