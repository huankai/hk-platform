package com.hk.emi.api.utils;

import com.hk.commons.util.SpringContextHolder;
import com.hk.emi.api.feign.SysCodeFeignClient;

/**
 * @author huangkai
 * @date 2019-01-22 14:05
 */
public abstract class CodeUtils {

    private static final SysCodeFeignClient CODE_FEIGN_CLIENT = SpringContextHolder.getBean(SysCodeFeignClient.class);

    /**
     * @param baseCodeId baseCodeId
     * @param value      value
     * @return CodeName
     */
    public static String getCodeName(Long baseCodeId, byte value) {
        return CODE_FEIGN_CLIENT.childCodeName(baseCodeId, value);
    }

}
