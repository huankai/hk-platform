package com.hk.sso.server.entity;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author: kevin
 * @date: 2018-08-02 16:51
 */
@Data
@Table(value = "sys_app")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysApp extends AbstractUUIDPersistable {

    @Column(value = "app_code")
    private String appCode;

    @Column(value = "app_name")
    private String appName;

    @Column(value = "app_icon")
    private String appIcon;

    @Column(value = "app_status")
    private Byte appStatus;

    @Column(value = "local_app")
    private Byte localApp;
}
