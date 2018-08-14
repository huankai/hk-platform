package com.hk.sso.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date 2018-08-02 14:25
 */
@Entity
@Data
@Table(name = "sys_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermission extends AbstractUUIDPersistable {

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "app_id")
    private String appId;

    @Column(name = "permission_code")
    private String permissionCode;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "permission_url")
    private String url;
}
