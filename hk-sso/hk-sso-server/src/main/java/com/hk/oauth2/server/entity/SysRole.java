package com.hk.sso.server.entity;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


/**
 * @author: kevin
 * @date: 2018-07-31 12:45
 */
@Data
@Table(value = "sys_role")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysRole extends AbstractUUIDPersistable {

    @Column(value = "app_id")
    private String appId;

    @Column(value = "role_name")
    private String roleName;

    @Column(value = "role_code")
    private String roleCode;

    @Column(value = "role_status")
    private Byte roleStatus;

    @Column(value = "description")
    private String description;

}
