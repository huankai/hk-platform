package com.hk.pms.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import com.hk.platform.commons.enums.UserStateEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author kevin
 * @date 2018-04-12 11:42
 */
@Data
@Entity
@Table(name = "sys_user")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUser extends AbstractSnowflakeAuditable {

    /**
     * 用户类型
     */
    private static final long USER_TYPE_DICT_BASE_ID = 308497981525594112L;

    @NotNull
    @Column(name = "org_id")
    private Long orgId;

//    @NotEmpty
//    @Column(name = "dept_id")
//    private Long deptId;

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

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Column(name = "user_type")
    private Byte userType;

    @NotNull
    @Column(name = "sex")
    private Byte sex;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "birth")
    private LocalDate birth;

//    @Column(name = "province_id")
//    private Long provinceId;
//
//    @Column(name = "city_id")
//    private Long cityId;
//
//    @Column(name = "area_id")
//    private Long areaId;

    /**
     * 用户状态
     *
     * @see UserStateEnum
     */
    @Column(name = "user_status")
//    @EnumByte(values = {1, 2, 8, 9}, notNull = true)
    private Byte userStatus;

    public LocalDateTime getRegisterDate() {
        return getCreatedDate().orElse(null);
    }

    public String getUserStatusText() {
        return UserStateEnum.getText(userStatus);
    }

    public String getUserStatusColor() {
        return UserStateEnum.getColor(userStatus);
    }

}
