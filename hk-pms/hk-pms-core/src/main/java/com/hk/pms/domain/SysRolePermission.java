package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_role_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysRolePermission extends AbstractAuditable {

    @NotEmpty
    @Column(value = "role_id")
    private String roleId;

    @NotEmpty
    @Column(value = "permission_id")
    private String permissionId;
}
