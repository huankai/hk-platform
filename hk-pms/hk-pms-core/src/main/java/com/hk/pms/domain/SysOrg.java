package com.hk.pms.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author kevin
 * @date 2018-04-12 11:41
 */
@Data
@Entity
@Table(name = "sys_org")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class SysOrg extends AbstractSnowflakeAuditable {

    @Column(name = "parent_id")
    private Long parentId;

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


}
