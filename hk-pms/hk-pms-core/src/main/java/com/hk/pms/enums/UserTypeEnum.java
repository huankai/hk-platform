package com.hk.pms.enums;

import com.hk.commons.util.TextValueItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-9-6 15:45
 */
@Getter
public enum UserTypeEnum {

    SYSTEM_ADMIN((byte) 1, "系统管理员"),

    ORG_ADMIN((byte) 2, "机构管理员"),

    GENERAL_USER((byte) 3, "普通用户");

    private byte value;

    private String text;

    UserTypeEnum(byte value, String text) {
        this.value = value;
        this.text = text;
    }

    public static List<TextValueItem> LIST;

    static {
        UserTypeEnum[] values = values();
        LIST = new ArrayList<>(values.length);
        for (UserTypeEnum item : values) {
            LIST.add(new TextValueItem(item.text, item.value));
        }
    }
}
