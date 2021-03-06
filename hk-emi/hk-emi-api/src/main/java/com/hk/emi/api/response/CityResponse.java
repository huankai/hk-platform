package com.hk.emi.api.response;

import lombok.Data;

import java.io.Serializable;

/**
 * City
 *
 * @author kevin
 * @date 2018-07-13 15:25
 */
@Data
@SuppressWarnings("serial")
public final class CityResponse implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * 编号
     */
    private String code;

    /**
     * 城市类型
     */
    private Byte cityType;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 简名
     */
    private String shortName;

    /**
     * 邮编
     */
    private String postOffice;

    /**
     * 描述
     */
    private String description;
}
