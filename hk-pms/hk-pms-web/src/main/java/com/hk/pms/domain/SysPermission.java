package com.hk.pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date 2018-04-12 16:29
 */
@Data
@Entity
@Table(name = "sys_permission")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermission extends ModelHolder.SysPermissionBase {
}
