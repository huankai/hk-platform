package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


/**
 * App 管理人员
 *
 * @author kevin
 * @date 2018-09-20 14:18
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(value = "sys_app_user")
public class SysAppUser extends AbstractAuditable {

    /**
     * App
     */
    @Column(value = "app_id")
    private String appId;

    /**
     * 续期类型
     */
    @Column(value = "user_id")
    private String userId;


}
