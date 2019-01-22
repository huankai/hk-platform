package com.hk.platform.commons.enums;

import com.hk.commons.annotations.EnumDisplay;

/**
 * 学历枚举
 *
 * @author huangkai
 * @date 2019-01-22 15:44
 */
public enum AcademicEnum {

    @EnumDisplay(order = 1, value = "小学及以下")
    XIAO_XUE,

    @EnumDisplay(order = 2, value = "初中")
    CHU_ZHONG,

    @EnumDisplay(order = 3, value = "高中")
    GAO_ZHONG,

    @EnumDisplay(order = 4, value = "中专")
    ZHON_GZHUAN,

    @EnumDisplay(order = 5, value = "大专")
    DA_ZHUAN,

    @EnumDisplay(order = 6, value = "本科")
    BEN_KE,

    @EnumDisplay(order = 7, value = "研究生")
    YAN_JIU,

    @EnumDisplay(order = 8, value = "硕士生")
    SHUO_SHI,

    @EnumDisplay(order = 9, value = "博士生")
    BO_SHI;


}
