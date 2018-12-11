package com.hk.oauth2.server.entity;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author: kevin
 * @date: 2018-08-02 16:51
 */
@Data
@Table(value = "sys_app")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysApp extends AbstractAuditable {

    @Column(value = "app_code")
    private String appCode;

    @Column(value = "app_name")
    private String appName;

    @Column(value = "app_host")
    private String appHost;

    @Column(value = "app_icon")
    private String appIcon;

    @Column(value = "start_date")
    private LocalDateTime startDate;

    @Column(value = "expire_date")
    private LocalDateTime expireDate;

    @Column(value = "app_status")
    private Byte appStatus;

    @Column(value = "description")
    private String description;

    @Column(value = "local_app")
    private Boolean localApp;
}
