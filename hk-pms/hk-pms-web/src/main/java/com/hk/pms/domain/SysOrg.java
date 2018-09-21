package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author: kevin
 * @date: 2018-04-12 11:41
 */
@Entity
@Table(name = "sys_org")
@Data
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysOrg extends AbstractAuditable {

    @Column(name = "parent_id")
    private String parentId;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "org_code")
    private String orgCode;

    @Column(name = "org_name")
    @Length(max = 20)
    @NotEmpty
    private String orgName;

    @Column(name = "description")
    @Length(max = 200)
    @NotEmpty
    private String description;

    @Column(name = "org_icon")
    private String orgIcon;

    @Column(name = "responsible_id")
    private String responsibleId;

    @Column(name = "org_tag")
    private String orgTag;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "area_id")
    private String areaId;

    @Column(name = "address")
    private String address;


}
