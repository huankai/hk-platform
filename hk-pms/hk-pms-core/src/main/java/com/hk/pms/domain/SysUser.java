package com.hk.pms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.commons.util.EnumDisplayUtils;
import com.hk.commons.validator.constraints.EnumByte;
import com.hk.commons.validator.constraints.EnumDict;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import com.hk.platform.commons.enums.UserStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author kevin
 * @date 2018-04-12 11:42
 */
@Data
@Table(value = "sys_user")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUser extends AbstractAuditable {

    /**
     * 用户类型
     */
    private static final String USER_TYPE_DICT_BASE_ID = "4028c081658f05b301658f0bf9b70005";

    @NotEmpty
    @Column(value = "org_id")
    private String orgId;

    @NotEmpty
    @Column(value = "dept_id")
    private String deptId;

    @NotEmpty
    @Length(max = 20)
    @Column(value = "account")
    private String account;

    @NotEmpty
    @Length(max = 11)
    @Column(value = "phone")
    private String phone;

    @Column(value = "email")
    private String email;

    @NotEmpty
    @Length(max = 20)
    @Column(value = "real_name")
    private String realName;

    @NotNull
    @Column(value = "password")
    @JsonIgnore
    private String password;

    @NotEmpty
    @EnumDict(codeId = USER_TYPE_DICT_BASE_ID)
    @Column(value = "user_type")
    private Byte userType;

    @NotNull
    @Column(value = "is_protect")
    private Boolean isProtect;

    @NotNull
    @Column(value = "sex")
    private Byte sex;

    @Column(value = "icon_path")
    private String iconPath;

    @Column(value = "birth")
    private LocalDate birth;

    @Column(value = "province_id")
    private String provinceId;

    @Column(value = "city_id")
    private String cityId;

    /**
     * 用户状态
     * @see UserStateEnum
     */
    @Column(value = "user_status")
    @EnumByte(values = {1, 2, 8, 9}, notNull = true)
    private Byte userStatus;

    public String getUserStatusChinese() {
        return EnumDisplayUtils.getDisplayText(UserStateEnum.class, userStatus);
    }
}
