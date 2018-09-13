package com.hk.pms.domain;


import com.hk.commons.validator.constraints.EnumByte;
import com.hk.commons.validator.constraints.EnumDict;
import com.hk.core.data.jpa.domain.AbstractAuditable;
import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * @author: kevin
 * @date 2018-04-12 11:25
 */
@SuppressWarnings("serial")
public class ModelHolder {

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysOrgBase extends AbstractAuditable {

        @Column(name = "org_code")
        @NotEmpty
        @Length(max = 20)
        private String orgCode;

        @Column(name = "org_name")
        @Length(max = 20)
        @NotEmpty
        private String orgName;

        @Column(name = "description")
        @Length(max = 200)
        @NotEmpty
        private String description;

        @Column(name = "org_icon")
        private String orgIcon;

        @Column(name = "responsible_id")
        private String responsibleId;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysOrgDeptBase extends AbstractAuditable {

        @Column(name = "org_id")
        @NotEmpty
        private String orgId;

        @Column(name = "dept_name")
        @NotEmpty
        @Length(max = 20)
        private String deptName;

        @Column(name = "responsible_id")
        @NotEmpty
        private String responsibleId;

        @Column(name = "description")
        @Length(max = 200)
        private String description;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysPermissionResourceBase extends AbstractUUIDPersistable {

        @Column(name = "permission_id")
        @NotEmpty
        private String permissionId;

        @Column(name = "resource_id")
        @NotEmpty
        private String resourceId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysResourceBase extends AbstractAuditable {

        static final String STATE_CODE_ID = "4028c081655a3a5a01655a3acd160001";

        @NotEmpty
        @Column(name = "parent_id")
        private String parentId;

        @Column(name = "app_id")
        @NotEmpty
        private String appId;

        @Column(name = "resource_name")
        @NotEmpty
        @Length(max = 30)
        private String resourceName;

        @Column(name = "resource_uri")
        @NotEmpty
        @Length(max = 20)
        private String resourceUri;

        @Column(name = "target")
        @NotEmpty
        @Length(max = 10)
        private String target;

        @Column(name = "ordered")
        @NotNull
        private Byte ordered;

        @Column(name = "resource_type")
        @EnumByte(values = {0})
        private Byte resourceType;

        @Column(name = "state")
        @EnumDict(codeId = STATE_CODE_ID)
        private Byte state;

        @Column(name = "icon")
        private String icon;

        @Column(name = "remark")
        private String remark;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysPermissionBase extends AbstractAuditable {

        @Column(name = "app_id")
        @NotEmpty
        private String appId;

        @Column(name = "permission_code")
        @NotEmpty
        @Length(max = 20)
        private String permissionCode;

        @Column(name = "permission_name")
        @NotEmpty
        @Length(max = 30)
        private String permissionName;

        @Column(name = "description")
        @Length(max = 200)
        private String description;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysUserRoleBase extends AbstractUUIDPersistable {

        @Column(name = "user_id")
        @NotEmpty
        private String userId;

        @Column(name = "role_id")
        @NotEmpty
        private String roleId;
    }


    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysDeptRoleBase extends AbstractUUIDPersistable {

        @Column(name = "dept_id")
        @NotEmpty
        private String deptId;

        @Column(name = "role_id")
        @NotEmpty
        private String roleId;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysUserIdcardBase extends AbstractAuditable {

        @Column(name = "user_id")
        @NotEmpty
        private String userId;

        @Column(name = "id_card")
        @NotEmpty
        @Length(max = 18)
        private String idCard;

        @Column(name = "real_name")
        @NotEmpty
        @Length(max = 20)
        private String realName;

        @Column(name = "expire_date")
        @NotNull
        @Past
        private LocalDate expireDate;

        @Column(name = "face_image")
        @NotEmpty
        private String faceImage;

        @Column(name = "back_image")
        @NotEmpty
        private String backImage;

        @NotEmpty
        @Length(max = 100)
        private String address;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysUserThirdBase extends AbstractAuditable {

        @Column(name = "user_id", nullable = false)
        @NotEmpty
        private String userId;

        @Column(name = "user_third_name", nullable = false)
        @NotEmpty
        @Length(max = 50)
        private String userThirdName;

        @Column(name = "open_id", nullable = false)
        @NotEmpty
        private String openId;

        @Column(name = "icon_url")
        private String iconUrl;

        @Column(name = "account_type", nullable = false)
        @EnumByte(values = {0, 1, 2, 3, 4})
        private Byte accountType;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysRoleBase extends AbstractAuditable {

        @Column(name = "app_id")
        @NotEmpty
        private String appId;

        @Column(name = "role_name")
        @NotEmpty
        @Length(max = 30)
        private String roleName;

        @Column(name = "role_code")
        @Length(max = 20)
        private String roleCode;

        @Column(name = "role_status")
        @EnumByte(values = {0, 1})
        private Byte roleStatus;

        @Column(name = "description")
        private String description;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysRolePermissionBase extends AbstractUUIDPersistable {

        @Column(name = "role_id")
        @NotEmpty
        private String roleId;

        @Column(name = "permission_id")
        @NotEmpty
        private String permissionId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysUserBase extends AbstractAuditable {

        /**
         * 用户类型
         */
        static final String USER_TYPE_DICT_BASE_ID = "4028c081658f05b301658f0bf9b70005";

        /**
         * 用户性别
         */
        static final String USER_SEX_DICT_BASE_ID = "4028c081658f05b301658f0bf9b70005";

        /**
         * 用户状态
         */
        static final String USER_STATUS_DICT_BASE_ID = "4028c081658f05b301658f0bf9b70005";

        @Column(name = "org_id")
        @NotEmpty
        private String orgId;

        @Column(name = "dept_id")
        @NotEmpty
        private String deptId;

        @Column(name = "account")
        @NotEmpty
        @Length(max = 20)
        private String account;

        @Column(name = "phone")
        @NotEmpty
        @Length(max = 11)
        private String phone;

        @Column(name = "email")
        private String email;

        @Column(name = "real_name")
        @NotEmpty
        @Length(max = 20)
        private String realName;

        @Column(name = "password")
        @NotNull
        private String password;

        @Column(name = "user_type")
        @NotEmpty
        @EnumDict(codeId = USER_TYPE_DICT_BASE_ID)
        private Byte userType;

        @Column(name = "is_protect")
        @NotNull
        private Boolean isProtect;

        @Column(name = "sex")
        @NotNull
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
    }


    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysAppBase extends AbstractAuditable {

        @Column(name = "app_name")
        @NotEmpty
        @Length(max = 100)
        private String appName;

        @Column(name = "app_code")
        @NotEmpty
        @Length(max = 50)
        private String appCode;

        @Column(name = "app_host")
        @NotEmpty
        @Length(max = 50)
        private String appHost;

        @Column(name = "app_icon")
        @NotEmpty
        @Length(max = 100)
        private String appIcon;

        @Column(name = "app_port")
        @NotNull
        @Max(9999)
        private Integer appPort;

        @Column(name = "app_status")
        @EnumByte(values = {0, 1})
        private Byte appStatus;

    }
}
