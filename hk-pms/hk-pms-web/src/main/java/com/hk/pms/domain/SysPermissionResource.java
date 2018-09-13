package com.hk.pms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: kevin
 * @date 2018-08-28 16:34
 */
@Data
@Entity
@Table(name = "sys_permission_resource")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysPermissionResource extends ModelHolder.SysPermissionResourceBase {
}
