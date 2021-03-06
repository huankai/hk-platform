package com.hk.pms.enums;

import com.hk.commons.util.TextValueItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-9-6 15:45
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserTypeEnum {

    SYSTEM_ADMIN((byte) 1, "系统管理员"),

    ORG_ADMIN((byte) 2, "机构管理员"),

    GENERAL_USER((byte) 3, "普通用户");

    private byte value;

    private String text;


    public static List<TextValueItem> LIST;

    static {
        UserTypeEnum[] values = values();
        LIST = new ArrayList<>(values.length);
        for (UserTypeEnum item : values) {
            LIST.add(new TextValueItem(item.text, item.value));
        }
    }

    public static String getUserTypeText(byte value) {
        for (UserTypeEnum userTypeEnum : values()) {
            if (userTypeEnum.value == value) {
                return userTypeEnum.text;
            }
        }
        return null;
    }
}
