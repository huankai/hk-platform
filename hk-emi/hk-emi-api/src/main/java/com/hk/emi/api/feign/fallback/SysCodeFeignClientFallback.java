package com.hk.emi.api.feign.fallback;

import com.hk.emi.api.feign.SysCodeFeignClient;
import com.hk.emi.api.response.SysCodeResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author kevin
 * @date 2018-08-31 16:37
 */
@Component
public class SysCodeFeignClientFallback implements SysCodeFeignClient {

    @Override
    public List<SysCodeResponse> childListByParentId(Long parentId, Long... ignores) {
        return Collections.emptyList();
    }

    @Override
    public List<String> childCodeNameList(Long parentId, Number... codeValues) {
        return Collections.emptyList();
    }


}
