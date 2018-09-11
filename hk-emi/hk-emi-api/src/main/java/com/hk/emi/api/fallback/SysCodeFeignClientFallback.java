package com.hk.emi.api.fallback;

import com.hk.emi.api.domain.SysCode;
import com.hk.emi.api.feign.SysCodeFeignClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author: kevin
 * @date 2018-08-31 16:37
 */
@Component
public class SysCodeFeignClientFallback implements SysCodeFeignClient {

    @Override
    public List<SysCode> childListByParentId(String parentId, String... ignores) {
        return Collections.emptyList();
    }

    @Override
    public List<String> childCodeNameList(String parentId, Byte... codeValues) {
        return Collections.emptyList();
    }


}
