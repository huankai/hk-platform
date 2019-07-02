package com.hk.pms.domain;

import com.hk.commons.validator.constraints.EnumByte;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "sys_app")
@SuppressWarnings("serial")
public class SysApp extends AbstractSnowflakeAuditable {

    @NotEmpty
    @Length(max = 50)
    @Column(name = "app_code")
    private String appCode;

    @NotEmpty
    @Length(max = 100)
    @Column(name = "app_name")
    private String appName;

    @NotEmpty
    @Length(max = 50)
    @Column(name = "app_host")
    private String appHost;

    @NotEmpty
    @Length(max = 100)
    @Column(name = "app_icon")
    private String appIcon;

    @Column(name = "app_status")
    @EnumByte(values = {0, 1})
    private Byte appStatus;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "description")
    private String description;

    @Column(name = "local_app")
    private Boolean localApp;


}
