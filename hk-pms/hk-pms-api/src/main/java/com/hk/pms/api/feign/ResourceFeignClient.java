package com.hk.pms.api.feign;

import com.hk.pms.commons.tree.ResourceTree;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-29 15:22
 */
@FeignClient(name = PmsService.SERVICE_NAME, path = PmsService.CONTEXT_PATH)
@RequestMapping("/api/resource")
public interface ResourceFeignClient {

    /**
     * 我的权限资源
     *
     * @param appId appId
     * @return
     */
    @GetMapping("{appId}")
    List<ResourceTree> myResourceList(@PathVariable("appId") String appId);


}
