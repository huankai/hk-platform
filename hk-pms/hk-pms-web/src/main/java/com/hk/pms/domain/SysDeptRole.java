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
 * @date: 2018-04-12 16:34
 */
@Data
@Entity
@Table(name = "sys_dept_role")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class SysDeptRole extends AbstractAuditable {

    @Column(name = "dept_id")
    @NotEmpty
    private String deptId;

    @Column(name = "role_id")
    @NotEmpty
    private String roleId;

}
