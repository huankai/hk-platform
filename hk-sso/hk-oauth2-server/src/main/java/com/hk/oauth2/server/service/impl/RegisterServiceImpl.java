package com.hk.oauth2.server.service.impl;

import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.ByteConstants;
import com.hk.core.service.exception.ServiceException;
import com.hk.oauth2.server.entity.SysUser;
import com.hk.oauth2.server.service.RegisterService;
import com.hk.oauth2.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author huangkai
 * @date 2019-01-09 14:30
 */
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

//    private final EmailFeign emailFeign;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private static final String HOST = "http://127.0.0.1:7100/oauth2";

    @Override
    public void emailRegister(String email, String password, String password2) {
        RegisterService.checkSamePassword(password, password2);
        SysUser user = new SysUser();
        user.setEmail(email);
        if (userService.count(user) > 0) {
            throw new ServiceException("账号已存在");
        }
        user.setUserStatus(ByteConstants.EIGHT);
        user.setIsProtect(false);
        user.setUserType(ByteConstants.TWO);
        user.setPassword(passwordEncoder.encode(password));
        SysUser sysUser = userService.insert(user);//TODO 数据库表中的必须字段没有设置

//        emailFeign.asyncSend(EmailMessageRequest.builder()
//                .subject("账号注册")
//                .text("尊敬的用户：您收到此邮件是因为你在 XXX 平台注册了账号，请点击 <a href='" + HOST + "/email/register-3/"
//                        + sysUser.getId()
//                        + "'> 这里 </a> 激活账号。")
//                .to(new String[]{email})
//                .build());
    }

    @Override
    @Transactional
    public void completeRegister(Long id, String realName, Byte sex, LocalDate birthday, String iconPath) {
        SysUser user = userService.getOne(id);
        user.setRealName(realName);
        user.setBirth(birthday);
        user.setSex(sex);
        user.setIconPath(iconPath);
        user.setUserStatus(ByteConstants.ONE);
        userService.updateById(user);
    }

    @Override
    public void checkEmailValidate(Long id) {
        Optional<SysUser> optionalUser = userService.findById(id);
        AssertUtils.isTrue(optionalUser.isPresent(), "账号信息不存在");
    }

}
