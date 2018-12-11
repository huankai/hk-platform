package com.hk.sso.server.entity;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

/**
 * @author: kevin
 * @date: 2018-07-25 08:45
 */
@Data
@Table("sys_user")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUser extends AbstractAuditable {

    @Column(value = "dept_id")
    private String deptId;

    @Column(value = "org_id")
    private String orgId;

    @Column(value = "account")
    private String account;

    @Column(value = "phone")
    private String phone;

    @Column(value = "email")
    private String email;

    @Column(value = "real_name")
    private String realName;

    @Column(value = "password")
    private String password;

    @Column(value = "user_type")
    private Byte userType;

    @Column(value = "is_protect")
    private Boolean isProtect;

    @Column(value = "sex")
    private Byte sex;

    @Column(value = "icon_path")
    private String iconPath;

    @Column(value = "birth")
    private LocalDate birth;

    @Column(value = "user_status")
    private Byte userStatus;

    @Column(value = "province_id")
    private String provinceId;

    @Column(value = "city_id")
    private String cityId;

}
