package com.hk.pms.api.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author huangkai
 * @date 2019-4-1 15:31
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class UserResponse implements Serializable {

    private String id;

    private String deptId;

    private String orgId;

    private String password;

    private String account;

    private String phone;

    private String email;

    private String realName;

    private Byte userType;

    private Byte sex;

    private String iconPath;

    private LocalDate birth;
}
