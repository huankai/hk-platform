package com.hk.sso.server.entity;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


/**
 * @author: kevin
 * @date: 2018-08-02 14:25
 */
@Data
@Table(value = "sys_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermission extends AbstractUUIDPersistable {

    @Column(value = "parent_id")
    private String parentId;

    @Column(value = "app_id")
    private String appId;

    @Column(value = "permission_code")
    private String permissionCode;

    @Column(value = "permission_name")
    private String permissionName;

    @Column(value = "permission_url")
    private String url;
}
