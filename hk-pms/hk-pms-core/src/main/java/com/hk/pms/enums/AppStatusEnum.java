package com.hk.pms.enums;

import com.hk.platform.commons.ui.TagColor;
import lombok.Getter;

/**
 * @author kevin
 * @date 2019-9-3 12:56
 */
@Getter
public enum AppStatusEnum {

    DISABLED(false, "已禁用", TagColor.RED),

    ENABLED(true, "已启用", TagColor.BLUE);

    private boolean state;

    private String text;

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
