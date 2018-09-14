package com.hk.pms.domain;

import com.hk.business.utils.DictCodeUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: kevin
 * @date: 2018-04-12 11:42
 */
@Data
@Entity
@Table(name = "sys_user")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUser extends ModelHolder.SysUserBase {

    public String getUserStatusChinese() {
        return DictCodeUtils.getChildName(USER_STATUS_DICT_BASE_ID, getUserStatus());
    }
}
