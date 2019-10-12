package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_role_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysRolePermission extends AbstractSnowflakeAuditable {

    @NotEmpty
    @Column(name = "role_id")
    private Long roleId;

    @NotEmpty
    @Column(name = "permission_id")
    private Long permissionId;
}
