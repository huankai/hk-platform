package com.hk.emi.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import com.hk.emi.enums.CityTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 城市表
 *
 * @author kevin
 * @date 2017年12月24日下午8:14:32
 */
@Data
@Entity
@Table(name = "emi_city")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class City extends AbstractSnowflakeAuditable {

    public static final long CITY_TYPE_DICT_ID = 12132424324L;

    /**
     * 行政代码
     */
    @NotEmpty
    @Length(max = 20)
    @Column(name = "code")
    private String code;

    @Column(name = "parent_id")
    private Long parentId;

    /**
     * <pre>
     * 城市类型:
     * 0:国家,
     * 1:省或直辖市,
     * 2:市,
     * 3:区或县,
     * </pre>
     */
    @Column(name = "city_type")
    @NotNull
//    @EnumDict(codeId = CITY_TYPE_DICT_ID)
    private Byte cityType;

    /**
     * 全称
     */
    @Column(name = "full_name")
    @NotEmpty
    @Length(max = 20)
    private String fullName;

    @Column(name = "area_code")
    private String areaCode;

    /**
     * 简名
     */
    @Column(name = "short_name")
    private String shortName;

    @Column(name = "merger_name")
    private String mergerName;

    /**
     * 邮编
     */
    @Column(name = "post_office")
    private String postOffice;

    /**
     * 拼音
     */
    @Column(name = "pinyin")
    private String pinyin;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    public String getCityTypeText() {
        return CityTypeEnum.getName(this.cityType);
    }

}
