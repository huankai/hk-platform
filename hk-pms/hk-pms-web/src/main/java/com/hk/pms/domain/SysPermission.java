package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:29
 */
@Data
@Table(value = "sys_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermission extends AbstractAuditable {

    @Column(value = "app_id")
    @NotEmpty
    private String appId;

    @Column(value = "permission_code")
    @NotEmpty
    @Length(max = 20)
    private String permissionCode;

    @Column(value = "permission_name")
    @NotEmpty
    @Length(max = 30)
    private String permissionName;

    @Column(value = "description")
    @Length(max = 200)
    private String description;
}
