package com.hk.business.utils;

import com.hk.commons.util.SpringContextHolder;
import com.hk.commons.validator.DictService;

/**
 * @author kevin
 * @date 2018-09-04 09:17
 */
public abstract class DictCodeUtils {

    private static final DictService DICT_SERVICE = SpringContextHolder.getBean(DictService.class);

    public static String getChildName(String baseId, Number value) {
        return DICT_SERVICE.getCodeName(baseId, value);
    }

}
