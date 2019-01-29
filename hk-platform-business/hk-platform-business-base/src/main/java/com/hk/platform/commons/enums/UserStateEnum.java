package com.hk.platform.commons.enums;

import com.hk.commons.annotations.EnumDisplay;

/**
 * 用户状态
 *
 * @author huangkai
 * @date 2019-01-28 14:19
 */
public enum UserStateEnum {

    @EnumDisplay(order = 1, value = "已禁用")
    DISABLED,

    @EnumDisplay(order = 2, value = "已启用")
    ENABLED,

    @EnumDisplay(order = 8, value = "注册中")
    REGISTERING,

    @EnumDisplay(order = 9, value = "已删除")
    DELETED;

}
