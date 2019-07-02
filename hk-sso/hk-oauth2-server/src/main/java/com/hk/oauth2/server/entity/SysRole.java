package com.hk.oauth2.server.entity;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author kevin
 * @date 2018-07-31 12:45
 */
@Data
@Entity
@Table(name = "sys_role")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysRole extends AbstractSnowflakeAuditable {

    @Column(name = "app_id")
    private Long appId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String roleCode;

    @Column(name = "role_status")
    private Byte roleStatus;

    @Column(name = "description")
    private String description;

}
