package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-08-28 16:34
 */
@Data
@Entity
@Table(name = "sys_permission_resource")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermissionResource extends AbstractAuditable {

    @Column(name = "permission_id")
    @NotEmpty
    private String permissionId;

    @Column(name = "resource_id")
    @NotEmpty
    private String resourceId;
}
