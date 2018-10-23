package com.hk.pms.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-04-12 11:41
 */
@Table(value = "sys_org")
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysOrg extends AbstractAuditable {

    @Column(value = "parent_id")
    private String parentId;

    @NotEmpty
    @Length(max = 20)
    @Column(value = "org_code")
    private String orgCode;

    @Column(value = "org_name")
    @Length(max = 20)
    @NotEmpty
    private String orgName;

    @Column(value = "description")
    @Length(max = 200)
    @NotEmpty
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
