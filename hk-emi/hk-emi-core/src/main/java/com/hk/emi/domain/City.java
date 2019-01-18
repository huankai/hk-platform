package com.hk.emi.domain;

import com.hk.commons.validator.constraints.EnumDict;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 城市表
 *
 * @author kevin
 * @date 2017年12月24日下午8:14:32
 */
@Data
@Table(value = "emi_city")
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class City extends AbstractAuditable {

    public static final String CITY_TYPE_DICT_ID = "4028c081655a3a5a01655a3acd160000";

    /**
     * 行政代码
     */
    @Column(value = "code")
    @NotEmpty
    @Length(max = 20)
    private String code;

    @Column(value = "parent_id")
    @NotEmpty
    private String parentId;

    /**
     * <pre>
     * 城市类型:
     * 0:国家,
     * 1:省或直辖市,
     * 2:市,
     * 3:区或县,
     * </pre>
     */
    @Column(value = "city_type")
    @NotNull
    @EnumDict(codeId = CITY_TYPE_DICT_ID)
    private Byte cityType;

    /**
     * 全称
     */
    @Column(value = "full_name")
    @NotEmpty
    @Length(max = 20)
    private String fullName;

    @Column(value = "area_code")
    private String areaCode;

    /**
     * 简名
     */
    @Column(value = "short_name")
    private String shortName;

    @Column(value = "merger_name")
    private String mergerName;

    /**
     * 邮编
     */
    @Column(value = "post_office")
    private String postOffice;

    /**
     * 拼音
     */
    @Column(value = "pinyin")
    private String pinyin;

    @Column(value = "longitude")
    private Double longitude;

    @Column(value = "latitude")
    private Double latitude;

    /**
     * 描述
     */
    @Column(value = "description")
    private String description;

}
