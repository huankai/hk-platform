package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:33
 */
@Data
@Table(value = "sys_user_role")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUserRole extends AbstractAuditable {

    @NotEmpty
    @Column(value = "user_id")
    private String userId;

    @NotEmpty
    @Column(value = "role_id")
    private String roleId;
}
