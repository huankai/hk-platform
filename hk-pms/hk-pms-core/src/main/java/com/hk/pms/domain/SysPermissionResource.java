package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author kevin
 * @date 2018-08-28 16:34
 */
@Data
@Entity
@Table(name = "sys_permission_resource")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermissionResource extends AbstractSnowflakeAuditable {

    @Column(name = "permission_id")
    @NotNull
    private Long permissionId;

    @Column(name = "resource_id")
    @NotNull
    private Long resourceId;
}
