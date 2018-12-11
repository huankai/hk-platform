package com.hk.oauth2.server.entity;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author: kevin
 * @date: 2018-10-25 15:08
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_org")
public class SysOrg extends AbstractAuditable {

    @Column(value = "parent_id")
    private String parentId;

    @Column(value = "org_code")
    private String orgCode;

    @Column(value = "org_name")
    private String orgName;

    @Column(value = "description")
    private String description;

    @Column(value = "org_icon")
    private String orgIcon;

    @Column(value = "responsible_id")
    private String responsibleId;

    @Column(value = "org_tag")
    private String orgTag;

    @Column(value = "province_id")
    private String provinceId;

    @Column(value = "city_id")
    private String cityId;

    @Column(value = "area_id")
    private String areaId;

    @Column(value = "address")
    private String address;


}
