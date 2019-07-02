package com.hk.pms.domain;

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
 * @date 2018-04-12 11:48
 */
@Entity
@Table(name = "sys_org_dept")
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysOrgDept extends AbstractSnowflakeAuditable {

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "org_id")
    @NotNull
    private Long orgId;

    @Column(name = "dept_name")
    @NotEmpty
    @Length(max = 20)
    private String deptName;

//    @Column(value = "responsible_id")
//    @NotEmpty
//    private String responsibleId;

    @Column(name = "description")
    @Length(max = 200)
    private String description;

}
