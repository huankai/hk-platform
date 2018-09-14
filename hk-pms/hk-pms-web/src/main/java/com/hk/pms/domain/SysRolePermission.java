package com.hk.pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date: 2018-04-12 16:36
 */
@Data
@Entity
@Table(name = "sys_role_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysRolePermission extends ModelHolder.SysRolePermissionBase {
}
