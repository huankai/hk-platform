package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-08-28 16:34
 */
@Data
@Table(value = "sys_permission_resource")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermissionResource extends AbstractAuditable {

    @Column(value = "permission_id")
    @NotEmpty
    private String permissionId;

    @Column(value = "resource_id")
    @NotEmpty
    private String resourceId;
}
