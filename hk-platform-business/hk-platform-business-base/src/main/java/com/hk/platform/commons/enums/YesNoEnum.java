package com.hk.platform.commons.enums;

import com.hk.commons.annotations.EnumDisplay;
import com.hk.commons.util.BooleanUtils;

/**
 * @author huangkai
 * @date 2019-01-22 15:53
 */
public enum YesNoEnum {

    @EnumDisplay(value = BooleanUtils.TRUE_CHINESE)
    YES,

    @EnumDisplay(order = 1, value = BooleanUtils.FALSE_CHINESE)
    NO;

}
