package com.hk.oauth2.server.entity;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author kevin
 * @date 2018-07-25 08:45
 */
@Data
@Entity
@Table(name = "sys_user")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUser extends AbstractSnowflakeAuditable {

//    @Column(name = "dept_id")
//    private Long deptId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "account")
    private String account;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type")
    private Byte userType;

//    @Column(name = "is_protect")
//    private Boolean isProtect;

    @Column(name = "sex")
    private Byte sex;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "user_status")
    private Byte userStatus;

//    @Column(name = "province_id")
//    private Long provinceId;
//
//    @Column(name = "city_id")
//    private Long cityId;

}
