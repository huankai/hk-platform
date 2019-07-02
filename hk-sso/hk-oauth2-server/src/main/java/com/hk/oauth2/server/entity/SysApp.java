package com.hk.oauth2.server.entity;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author kevin
 * @date 2018-08-02 16:51
 */
@Data
@Entity
@Table(name = "sys_app")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysApp extends AbstractSnowflakeAuditable {

    @Column(name = "app_code")
    private String appCode;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "app_host")
    private String appHost;

    @Column(name = "app_icon")
    private String appIcon;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "app_status")
    private Byte appStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "local_app")
    private Boolean localApp;
}
