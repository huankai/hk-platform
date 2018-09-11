package com.hk.emi.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: kevin
 * @date 2018-07-13 15:30
 */
@Data
@SuppressWarnings("serial")
public final class SysCode implements Serializable {

    private String id;

    private String childCode;

    private Byte codeValue;

    private String codeName;

    private String description;

}
