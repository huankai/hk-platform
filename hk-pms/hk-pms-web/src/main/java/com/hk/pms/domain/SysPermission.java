package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-04-12 16:29
 */
@Data
@Entity
@Table(name = "sys_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermission extends AbstractAuditable {

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
