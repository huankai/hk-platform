package com.hk.emi.domain;

import com.hk.commons.util.ByteConstants;
import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * 城市表
 *
 * @author: kevin
 * @date 2017年12月24日下午8:14:32
 */
@Data
@Entity
@Table(name = "sys_city")
@EqualsAndHashCode(callSuper = true)
public class City extends AbstractAuditable {

    /**
     * 行政代码
     */
    @Column(name = "code")
    @NotBlank(message = "")
    @Length(max = 20, message = "")
    private String code;


    @Column(name = "parent_id")
    @NotBlank(message = "")
    private String parentId;

    /**
     * <p>
     * 城市类型:
     * 1:国家,
     * 2:省或直辖市,
     * 3:市,
     * 4:区或县,
     * 5:镇,
     * 6:村
     * </p>
     *
     * @see CityType
     */
    @Column(name = "city_type")
    @NotNull
    @Range(max = 6)
    private Byte cityType;

    /**
     * 全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 简名
     */
    @Column(name = "short_name")
    private String shortName;

    /**
     * 邮编
     */
    @Column(name = "post_office")
    private String postOffice;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 城市类型级别
     */
    public enum CityType {

        COUNTRY(ByteConstants.ONE, "国家"),

        PROVINCE(ByteConstants.TWO, "省"),

        CITY(ByteConstants.THREE, "市"),

        AREA(ByteConstants.FOUR, "区或县"),

        TOWN(ByteConstants.FIVE, "镇"),

        VILLAGE(ByteConstants.SIX, "村");

        private Byte cityType;

        private String value;

        CityType(Byte cityType, String value) {
            this.cityType = cityType;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public byte getCityType() {
            return cityType;
        }
    }

    public String getCityTypeChinease() {
        CityType[] values = CityType.values();
        return Arrays.stream(values)
                .filter(item -> item.cityType.equals(cityType))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Parameters that can not be identified.paramter value :" + cityType))
                .value;
    }

}
