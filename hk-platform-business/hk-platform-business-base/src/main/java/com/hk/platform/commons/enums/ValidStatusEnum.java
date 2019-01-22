package com.hk.platform.commons.enums;

import com.hk.commons.annotations.EnumDisplay;

/**
 * @author huangkai
 * @date 2019-01-22 15:50
 */
public enum ValidStatusEnum {

    @EnumDisplay(value = "有效")
    EFFECTIVE,

    @EnumDisplay(order = 1, value = "无效")
    INVALID;

}
