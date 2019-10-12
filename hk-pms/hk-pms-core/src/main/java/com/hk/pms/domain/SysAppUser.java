package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * App 管理人员
 *
 * @author kevin
 * @date 2018-09-20 14:18
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_app_user")
public class SysAppUser extends AbstractSnowflakeAuditable {

    /**
     * App
     */
    @Column(name = "app_id")
    private Long appId;

    /**
     * 用户类型
     */
    @Column(name = "user_id")
    private Long userId;


}
