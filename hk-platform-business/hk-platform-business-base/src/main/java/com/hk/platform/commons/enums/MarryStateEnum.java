package com.hk.platform.commons.enums;

import com.hk.commons.annotations.EnumDisplay;

/**
 * 婚姻状态
 *
 * @author huangkai
 * @date 2019-01-22 15:40
 */
public enum MarryStateEnum {

    @EnumDisplay(order = 1, value = "已婚")
    MARRIED,

    @EnumDisplay(order = 2, value = "未婚")
    UN_MARRIED

}
