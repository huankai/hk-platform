package com.hk.sso.server.entity;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: kevin
 * @date: 2018-08-02 16:51
 */
@Data
@Entity
@Table(name = "sys_app")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysApp extends AbstractUUIDPersistable {

    @Column(name = "app_code")
    private String appCode;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_icon")
    private String appIcon;

    @Column(name = "app_status")
    private Byte appStatus;

    @Column(name = "local_app")
    private Byte localApp;
}
