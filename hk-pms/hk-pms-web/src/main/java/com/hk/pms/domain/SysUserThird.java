package com.hk.pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date: 2018-04-12 16:35
 */
@Data
@Entity
@Table(name = "sys_user_third")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUserThird extends ModelHolder.SysUserThirdBase {
}
