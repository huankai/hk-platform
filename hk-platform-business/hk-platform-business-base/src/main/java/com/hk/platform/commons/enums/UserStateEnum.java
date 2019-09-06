package com.hk.platform.commons.enums;

import com.hk.commons.util.TextValueItem;
import com.hk.platform.commons.ui.TagColor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户状态
 *
 * @author huangkai
 * @date 2019-01-28 14:19
 */
@Getter
public enum UserStateEnum {

    DISABLED((byte) 0, TagColor.GRAY, "已禁用"),

    ENABLED((byte) 1, TagColor.BLUE, "已启用"),

    DELETED((byte) 9, TagColor.RED, "已删除");

    private byte value;

    private String color;

    private String text;

    UserStateEnum(byte value, String color, String text) {
        this.value = value;
        this.color = color;
        this.text = text;
    }

    public static List<TextValueItem> LIST;

    static {
        UserStateEnum[] values = values();
        LIST = new ArrayList<>(values.length);
        for (UserStateEnum item : values()) {
            LIST.add(new TextValueItem(item.text, item.value));
        }
    }

    public static String getColor(byte value) {
        for (UserStateEnum item : values()) {
            if (item.getValue() == value) {
                return item.color;
            }
        }
        return null;
    }

    public static String getText(byte value) {
        for (UserStateEnum item : values()) {
            if (item.getValue() == value) {
                return item.text;
            }
        }
        return null;
    }
}
