package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumDict;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:35
 */
@Data
@Entity
@Table(name = "sys_user_third")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUserThird extends AbstractSnowflakeAuditable {

    private static final long ACCOUNT_TYPE = 308498909662154752L;

    @NotEmpty
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty
    @Length(max = 50)
    @Column(name = "user_third_name")
    private String userThirdName;

    @NotEmpty
    @Column(name = "open_id")
    private String openId;

    @Column(name = "icon_url")
    private String iconUrl;

    /**
     *
     */
    @EnumDict(codeId = ACCOUNT_TYPE)
    @Column(name = "account_type")
    private Byte accountType;
}
