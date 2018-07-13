package com.hk.emi.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: kevin
 * @date 2018-07-13 15:30
 */
@Data
public class SysCode implements Serializable {

    private String id;

    private String code;

    private String codeName;

    private String description;

}
