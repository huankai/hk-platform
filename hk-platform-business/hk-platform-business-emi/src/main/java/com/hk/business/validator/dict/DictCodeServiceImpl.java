package com.hk.business.validator.dict;

import com.hk.commons.validator.DictService;
import com.hk.emi.api.domain.SysCode;
import com.hk.emi.api.feign.SysCodeFeignClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2018-08-31 14:29
 */
public class DictCodeServiceImpl implements DictService {

    private SysCodeFeignClient codeFeignClient;

    public DictCodeServiceImpl(SysCodeFeignClient codeFeignClient) {
        this.codeFeignClient = codeFeignClient;
    }

    @Override
    public List<Byte> getDictValueListByCodeId(String codeId) {
        List<SysCode> sysCodeList = codeFeignClient.childListByParentId(codeId);
        return sysCodeList.stream().map(SysCode::getCodeValue).collect(Collectors.toList());
    }

    @Override
    public String getCodeName(String baseCodeId, byte value) {
        return codeFeignClient.childCodeName(baseCodeId, value);
    }
}
