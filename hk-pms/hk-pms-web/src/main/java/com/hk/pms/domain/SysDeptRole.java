package com.hk.pms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: kevin
 * @date 2018-04-12 16:34
 */
@Data
@Entity
@Table(name = "sys_dept_role")
@EqualsAndHashCode(callSuper = false)
public class SysDeptRole extends ModelHolder.SysDeptRoleBase {

}
