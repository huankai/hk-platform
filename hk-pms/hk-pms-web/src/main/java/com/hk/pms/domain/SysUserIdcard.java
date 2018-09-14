package com.hk.pms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户身份证信息实体
 *
 * @author: kevin
 * @date: 2018-08-31 11:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_user_idcard")
public class SysUserIdcard extends ModelHolder.SysUserIdcardBase {
}
