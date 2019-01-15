package com.hk.oauth2.server.service;

import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.StringUtils;
import org.springframework.util.Base64Utils;

import java.time.LocalDate;

/**
 * @author huangkai
 * @date 2019-01-09 14:29
 */
public interface RegisterService {

    /**
     * 检查密码是否匹配
     *
     * @param password  password
     * @param password2 password2
     */
    static void checkSamePassword(String password, String password2) {
        AssertUtils.isTrue(StringUtils.equals(password, password2), "两次输入密码不一致");
    }

    /**
     * 邮箱注册
     *
     * @param email     email
     * @param password  password
     * @param password2 password2
     */
    void emailRegister(String email, String password, String password2);

    /**
     * 默认使用base64编码id
     *
     * @param id id
     */
    default String encodeToString(String id) {
        return Base64Utils.encodeToString(id.getBytes());
    }

    /**
     * 默认使用base64解码id
     *
     * @param id id
     */
    default String decodeToString(String id) {
        return new String(Base64Utils.decode(id.getBytes()));
    }

    /**
     * 检查邮箱验证码
     *
     * @param id id
     */
    void checkEmailValidate(String id);

    /**
     * 完成注册
     *
     * @param id       id
     * @param realName 真实名
     * @param sex      性别
     * @param birthday 生日
     * @param iconPath 头像
     */
    void completeRegister(String id, String realName, Byte sex, LocalDate birthday, String iconPath);

}
