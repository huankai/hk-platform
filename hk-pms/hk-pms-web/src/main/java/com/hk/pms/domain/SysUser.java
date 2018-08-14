package com.hk.pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date 2018-04-12 11:42
 */
@Data
@Entity
@Table(name = "sys_user")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUser extends ModelHolder.SysUserBase {
}
