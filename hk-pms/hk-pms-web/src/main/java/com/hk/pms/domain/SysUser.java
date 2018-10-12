package com.hk.pms.domain;

import com.hk.business.utils.DictCodeUtils;
import com.hk.commons.validator.constraints.EnumDict;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author: kevin
 * @date: 2018-04-12 11:42
 */
@Data
@Entity
@Table(name = "sys_user")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUser extends AbstractAuditable {


    /**
     * 用户类型
     */
    private static final String USER_TYPE_DICT_BASE_ID = "4028c081658f05b301658f0bf9b70005";

    /**
     * 用户性别
     */
    private static final String USER_SEX_DICT_BASE_ID = "4028c081658f05b301658f0bf9b70005";

    /**
     * 用户状态
     */
    private static final String USER_STATUS_DICT_BASE_ID = "4028c081658f05b301658f0bf9b70005";

    @NotEmpty
    @Column(name = "org_id")
    private String orgId;

    @NotEmpty
    @Column(name = "dept_id")
    private String deptId;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "account")
    private String account;

    @NotEmpty
    @Length(max = 11)
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "real_name")
    private String realName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotEmpty
    @EnumDict(codeId = USER_TYPE_DICT_BASE_ID)
    @Column(name = "user_type")
    private Byte userType;

    @NotNull
    @Column(name = "is_protect")
    private Boolean isProtect;

    @NotNull
    @Column(name = "sex")
    @EnumDict(codeId = USER_SEX_DICT_BASE_ID)
    private Byte sex;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "user_status")
    @EnumDict(codeId = USER_STATUS_DICT_BASE_ID)
    private Byte userStatus;

    public String getUserStatusChinese() {
        return DictCodeUtils.getChildName(USER_STATUS_DICT_BASE_ID, getUserStatus());
    }
}
