package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-04-12 11:48
 */
@Table(value = "sys_org_dept")
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysOrgDept extends AbstractAuditable {

    @Column(value = "parent_id")
    private String parentId;

    @Column(value = "org_id")
    @NotEmpty
    private String orgId;

    @Column(value = "dept_name")
    @NotEmpty
    @Length(max = 20)
    private String deptName;

//    @Column(value = "responsible_id")
//    @NotEmpty
//    private String responsibleId;

    @Column(value = "description")
    @Length(max = 200)
    private String description;

}
