package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:36
 */
@Data
@Table(value = "sys_role")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysRole extends AbstractAuditable {

    @NotEmpty
    @Column(value = "app_id")
    private String appId;

    @NotEmpty
    @Length(max = 30)
    @Column(value = "role_name")
    private String roleName;

    @Length(max = 20)
    @Column(value = "role_code")
    private String roleCode;

    @EnumByte(values = {0, 1})
    @Column(value = "role_status")
    private Byte roleStatus;

    @Column(value = "description")
    private String description;
}
