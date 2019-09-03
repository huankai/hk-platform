package com.hk.pms.enums;

import com.hk.platform.commons.enums.StatusUtils;
import lombok.Getter;

/**
 * @author kevin
 * @date 2019-9-3 12:56
 */
public enum AppStatusEnum {

    DISABLED(false, "已禁用", StatusUtils.RED),

    ENABLED(true, "已启用", StatusUtils.BLUE);

    @Getter
    private boolean state;

    @Getter
    private String text;

    @Getter
    private String color;

    AppStatusEnum(boolean state, String text, String color) {
        this.state = state;
        this.text = text;
        this.color = color;
    }

    public static String getText(boolean state) {
        return state ? ENABLED.text : DISABLED.text;
    }

    public static String getColor(boolean state) {
        return state ? ENABLED.color : DISABLED.color;
    }
}
