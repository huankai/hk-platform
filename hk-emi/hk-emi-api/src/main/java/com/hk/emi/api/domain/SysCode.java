package com.hk.emi.api.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @author: kevin
 * @date 2018-07-13 15:30
 */
@Data
@SuppressWarnings("serial")
public class SysCode implements Serializable {

    private String id;

    private String code;

    private String codeName;

    private String description;

}
