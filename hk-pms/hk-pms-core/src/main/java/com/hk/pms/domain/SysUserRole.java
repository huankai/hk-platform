package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author kevin
 * @date 2018-04-12 16:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "sys_user_role")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUserRole extends AbstractSnowflakeAuditable {

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty
    @Column(name = "role_id")
    private Long roleId;
}
