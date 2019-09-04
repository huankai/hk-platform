package com.hk.platform.commons.enums;

import com.hk.commons.util.BooleanUtils;
import lombok.Getter;

/**
 * @author huangkai
 * @date 2019-01-22 15:53
 */
@Getter
public enum YesNoEnum {

    YES(true, StatusUtils.BLUE, BooleanUtils.TRUE_CHINESE),

    NO(false, StatusUtils.RED, BooleanUtils.FALSE_CHINESE);

    private boolean state;

    private String color;

    private String text;

    YesNoEnum(boolean state, String color, String text) {
        this.state = state;
        this.color = color;
        this.text = text;
    }

    public static String getColor(boolean state) {
        return state ? YES.color : NO.color;
    }

    public static String getText(boolean state) {
        return state ? YES.text : NO.text;
    }
}
