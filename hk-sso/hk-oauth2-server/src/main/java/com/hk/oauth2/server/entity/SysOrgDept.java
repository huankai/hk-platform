package com.hk.oauth2.server.entity;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author kevin
 * @date 2018-10-25 15:12
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_org_dept")
public class SysOrgDept extends AbstractAuditable {

    @Column(value = "org_id")
    private String orgId;

    @Column(value = "parent_id")
    private String parentId;

    @Column(value = "dept_name")
    private String deptName;

    @Column(value = "description")
    private String description;

}
