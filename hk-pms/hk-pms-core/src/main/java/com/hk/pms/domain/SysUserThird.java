package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 16:35
 */
@Data
@Table(value = "sys_user_third")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysUserThird extends AbstractAuditable {

    @NotEmpty
    @Column(value = "user_id")
    private String userId;

    @NotEmpty
    @Length(max = 50)
    @Column(value = "user_third_name")
    private String userThirdName;

    @NotEmpty
    @Column(value = "open_id")
    private String openId;

    @Column(value = "icon_url")
    private String iconUrl;

    @EnumByte(values = {0, 1, 2, 3, 4})
    @Column(value = "account_type")
    private Byte accountType;
}
