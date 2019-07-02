package com.hk.business.validator.dict;

import com.hk.commons.validator.DictService;
import com.hk.emi.api.feign.SysCodeFeignClient;
import com.hk.emi.api.response.SysCodeResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-08-31 14:29
 */
public class FeignDictCodeServiceImpl implements DictService {

    private SysCodeFeignClient codeFeignClient;

    public FeignDictCodeServiceImpl(SysCodeFeignClient codeFeignClient) {
        this.codeFeignClient = codeFeignClient;
    }

    @Override
    public List<Byte> getDictValueListByCodeId(Long codeId) {
        List<SysCodeResponse> sysCodeList = codeFeignClient.childListByParentId(codeId);
        return sysCodeList.stream().map(SysCodeResponse::getCodeValue).collect(Collectors.toList());
    }

    @Override
    public String getCodeName(Long baseCodeId, Number value) {
        return codeFeignClient.childCodeName(baseCodeId, value);
    }
}
