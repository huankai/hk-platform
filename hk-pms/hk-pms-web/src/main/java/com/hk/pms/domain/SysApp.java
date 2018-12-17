package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

/**
 * App Entity
 *
 * @author kevin
 * @date 2018-04-12 11:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(value = "sys_app")
@SuppressWarnings("serial")
public class SysApp extends AbstractAuditable {

    @NotEmpty
    @Length(max = 50)
    @Column(value = "app_code")
    private String appCode;

    @NotEmpty
    @Length(max = 100)
    @Column(value = "app_name")
    private String appName;

    @NotEmpty
    @Length(max = 50)
    @Column(value = "app_host")
    private String appHost;

    @NotEmpty
    @Length(max = 100)
    @Column(value = "app_icon")
    private String appIcon;

    @Column(value = "app_status")
    @EnumByte(values = {0, 1})
    private Byte appStatus;

    @Column(value = "start_date")
    private LocalDate startDate;

    @Column(value = "expire_date")
    private LocalDate expireDate;

    @Column(value = "description")
    private String description;

    @Column(value = "local_app")
    private Boolean localApp;


}
