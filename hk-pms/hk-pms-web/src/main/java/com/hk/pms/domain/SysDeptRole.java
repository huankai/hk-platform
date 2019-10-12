package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-04-12 16:34
 */
@Data
@Table(value = "sys_dept_role")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class SysDeptRole extends AbstractAuditable {

    @Column(value = "dept_id")
    @NotEmpty
    private String deptId;

    @Column(value = "role_id")
    @NotEmpty
    private String roleId;

}
