package com.hk.pms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: kevin
 * @date 2018-08-28 16:35
 */
@Data
@Entity
@Table(name = "sys_resource")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysResource extends ModelHolder.SysResourceBase {

}
