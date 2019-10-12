package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sys_dept_role")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class SysDeptRole extends AbstractSnowflakeAuditable {

    @Column(name = "dept_id")
    @NotEmpty
    private Long deptId;

    @Column(name = "role_id")
    @NotEmpty
    private Long roleId;

}
