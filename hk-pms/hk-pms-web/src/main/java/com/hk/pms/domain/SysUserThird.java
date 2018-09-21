package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-04-12 16:35
 */
@Data
@Entity
@Table(name = "sys_user_third")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUserThird extends AbstractAuditable {

    @NotEmpty
    @Column(name = "user_id", nullable = false)
    private String userId;

    @NotEmpty
    @Length(max = 50)
    @Column(name = "user_third_name", nullable = false)
    private String userThirdName;

    @NotEmpty
    @Column(name = "open_id", nullable = false)
    private String openId;

    @Column(name = "icon_url")
    private String iconUrl;

    @EnumByte(values = {0, 1, 2, 3, 4})
    @Column(name = "account_type", nullable = false)
    private Byte accountType;
}
