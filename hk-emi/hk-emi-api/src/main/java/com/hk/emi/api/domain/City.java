package com.hk.emi.api.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * City
 *
 * @author: kevin
 * @date 2018-07-13 15:25
 */
@Data
@SuppressWarnings("serial")
public class City implements Serializable {

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
