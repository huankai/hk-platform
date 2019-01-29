package com.hk.pms.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@SuppressWarnings("serial")
public class User implements Serializable {

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
