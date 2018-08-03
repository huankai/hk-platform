package com.hk.sso.server.entity;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: kevin
 * @date 2018-07-31 12:45
 */
@Entity
@Data
@Table(name = "sys_role")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends AbstractUUIDPersistable {

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
