package com.hk.emi.api.feign;

import com.hk.commons.util.CollectionUtils;
import com.hk.emi.api.feign.fallback.SysCodeFeignClientFallback;
import com.hk.emi.api.response.SysCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kevin
 * @date 2018-07-13 15:27
 */
@FeignClient(name = EmiService.SERVICE_NAME, path = EmiService.CONTEXT_PATH,
        fallback = SysCodeFeignClientFallback.class)
@RequestMapping("/api/code")
public interface SysCodeFeignClient {

    /**
     * @param parentId parentId
     * @return SysCode
     */
    @GetMapping("/child")
    List<SysCodeResponse> childListByParentId(@RequestParam("parentId") Long parentId, @RequestParam("ignore") Long... ignores);

    /**
     * @param parentId  parentId
     * @param codeValue codeValue
     * @return String
     */
    default String childCodeName(Long parentId, Number codeValue) {
        return CollectionUtils.getFirstOrDefault(childCodeNameList(parentId, codeValue)).orElse(null);
    }

    /**
     * @param parentId   parentId
     * @param codeValues codeValue
     * @return List<String>
     */
    @GetMapping("/childnames")
    List<String> childCodeNameList(@RequestParam("parentId") Long parentId, @RequestParam("code_values") Number... codeValues);
}
