package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-04-12 11:48
 */
@Table(name = "sys_org_dept")
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysOrgDept extends AbstractAuditable {

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "org_id")
    @NotEmpty
    private String orgId;

    @Column(name = "dept_name")
    @NotEmpty
    @Length(max = 20)
    private String deptName;

    @Column(name = "responsible_id")
    @NotEmpty
    private String responsibleId;

    @Column(name = "description")
    @Length(max = 200)
    private String description;

}
