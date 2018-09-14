package com.hk.pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * App Entity
 *
 * @author: kevin
 * @date: 2018-04-12 11:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_app")
@SuppressWarnings("serial")
public class SysApp extends ModelHolder.SysAppBase {


}
