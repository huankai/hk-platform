package com.hk.emi.enums;

import com.hk.commons.util.TextValueItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author huangkai
 * @date 2019-08-31 08:12
 */
public enum CityTypeEnum {

    COUNTRY("国家", (byte) 0),

    PROVINCE("省", (byte) 1),

    CITY("市", (byte) 2),

    AREA("区/县", (byte) 3),

    TOWN("镇/乡", (byte) 4);

    @Getter
    private String name;

    @Getter
    private byte value;

    CityTypeEnum(String name, byte value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(byte value) {
        for (CityTypeEnum typeEnum : values()) {
            if (typeEnum.value == value) {
                return typeEnum.name;
            }
        }
        return null;
    }

    public static final List<TextValueItem> LIST;

    static {
        CityTypeEnum[] values = values();
        LIST = new ArrayList<>(values.length);
        for (CityTypeEnum item : values) {
            LIST.add(new TextValueItem(item.name, item.value));
        }
    }
}
