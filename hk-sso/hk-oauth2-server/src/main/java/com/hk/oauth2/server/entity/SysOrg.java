package com.hk.oauth2.server.entity;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author kevin
 * @date 2018-10-25 15:08
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_org")
public class SysOrg extends AbstractSnowflakeAuditable {

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "org_code")
    private String orgCode;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "description")
    private String description;

    @Column(name = "org_icon")
    private String orgIcon;

    @Column(name = "responsible_id")
    private Long responsibleId;

    @Column(name = "org_tag")
    private String orgTag;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "address")
    private String address;
    
    @Column(name = "state")
    private Byte state;


}
