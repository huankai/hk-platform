package com.hk.pms.domain;


import com.hk.core.data.jpa.domain.AbstractAuditable;
import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
        @NotBlank
        @Length(max = 20)
        private String orgCode;

        @Column(name = "org_name")
        @Length(max = 20)
        @NotBlank
        private String orgName;

        @Column(name = "description")
        @Length(max = 200)
        @NotBlank
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
        private String orgId;

        @Column(name = "dept_name")
        @NotBlank
        @Length(max = 20)
        private String deptName;

        @Column(name = "responsible_id")
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
        private String permissionId;

        @Column(name = "resource_id")
        private String resourceId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysResourceBase extends AbstractAuditable {

        @Column(name = "parent_id")
        private String parentId;

        @Column(name = "app_id")
        private String appId;

        @Column(name = "resource_name")
        private String resourceName;

        @Column(name = "resource_uri")
        private String resourceUri;

        @Column(name = "target")
        private String target;

        @Column(name = "ordered")
        private Byte ordered;

        @Column(name = "resource_type")
        private Byte resourceType;

        @Column(name = "state")
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
        private String appId;

        @Column(name = "permission_code")
        private String permissionCode;

        @Column(name = "permission_name")
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
        private String userId;

        @Column(name = "role_id")
        private String roleId;
    }


    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysDeptRoleBase extends AbstractUUIDPersistable {

        @Column(name = "dept_id")
        private String deptId;

        @Column(name = "role_id")
        private String roleId;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysUserThirdBase extends AbstractAuditable {

        @Column(name = "user_id", nullable = false)
        @NotBlank
        private String userId;

        @Column(name = "user_third_name", nullable = false)
        @NotBlank
        @Length(max = 50)
        private String userThirdName;

        @Column(name = "open_id", nullable = false)
        @NotBlank
        private String openId;

        @Column(name = "icon_url")
        private String iconUrl;

        @Column(name = "account_type", nullable = false)
        @NotNull
        private Byte accountType;
    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysRoleBase extends AbstractAuditable {

        @Column(name = "app_id")
        private String appId;

        @Column(name = "role_name")
        private String roleName;

        @Column(name = "role_code")
        private String roleCode;

        @Column(name = "role_status")
        private Byte roleStatus;

        @Column(name = "description")
        private String description;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysRolePermissionBase extends AbstractUUIDPersistable {

        @Column(name = "role_id")
        private String roleId;

        @Column(name = "permission_id")
        private String permissionId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysUserBase extends AbstractAuditable {

        @Column(name = "org_id")
        private String orgId;

        @Column(name = "dept_id")
        private String deptId;

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

        @Column(name = "is_protect")
        private Boolean isProtect;

        @Column(name = "sex")
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
        private Byte userStatus;
    }


    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    static class SysAppBase extends AbstractAuditable {

        @Column(name = "app_name")
        private String appName;

        @Column(name = "app_code")
        private String appCode;

        @Column(name = "app_ip")
        private String appIp;

        @Column(name = "app_icon")
        private String appIcon;

        @Column(name = "app_port")
        private Integer appPort;

        @Column(name = "app_status")
        private Byte appStatus;

    }
}
