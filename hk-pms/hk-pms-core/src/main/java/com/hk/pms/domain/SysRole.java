package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author kevin
 * @date 2018-04-12 16:36
 */
@Data
@Entity
@Table(name = "sys_role")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysRole extends AbstractSnowflakeAuditable {

    @NotNull
    @Column(name = "app_id")
    private Long appId;

    @NotEmpty
    @Length(max = 30)
    @Column(name = "role_name")
    private String roleName;

    @Length(max = 20)
    @Column(name = "role_code")
    private String roleCode;

    @EnumByte(values = {0, 1})
    @Column(name = "role_status")
    private Byte roleStatus;

    @Column(name = "description")
    private String description;
}
