package com.hk.platform.commons.enums;

import lombok.Getter;

/**
 * @author kevin
 * @date 2018-08-29 16:42
 */
@Getter
public enum SexEnum {

    MAN((byte) 1, "男"),

    WUMAN((byte) 2, "女"),

    UNKNOWN((byte) 9, "未采集");

    private byte value;

    private String text;

    SexEnum(byte value, String text) {
        this.value = value;
        this.text = text;
    }
}
