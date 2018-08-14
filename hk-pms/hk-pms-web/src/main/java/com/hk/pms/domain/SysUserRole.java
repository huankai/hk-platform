package com.hk.pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date 2018-04-12 16:33
 */
@Data
@Entity
@Table(name = "sys_user_role")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUserRole extends ModelHolder.SysUserRoleBase {
}
