package com.hk.pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date 2018-04-12 11:41
 */
@Entity
@Table(name = "sys_org")
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysOrg extends ModelHolder.SysOrgBase {
}
