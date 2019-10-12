package com.hk.platform.commons.enums;

import com.hk.commons.annotations.EnumDisplay;

/**
 * @author kevin
 * @date 2018-08-29 16:42
 */
public enum SexEnum {

    @EnumDisplay(order = 1, value = "男")
    MAN,

    @EnumDisplay(order = 2, value = "女")
    WUMAN,

    @EnumDisplay(order = 9, value = "未知")
    UNKNOWN;
}
