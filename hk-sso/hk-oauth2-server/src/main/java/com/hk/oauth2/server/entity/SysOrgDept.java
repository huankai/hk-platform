package com.hk.oauth2.server.entity;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author kevin
 * @date 2018-10-25 15:12
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_org_dept")
public class SysOrgDept extends AbstractSnowflakeAuditable {

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "description")
    private String description;

}
