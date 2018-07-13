package com.hk.emi.api.feign;

import com.hk.emi.api.domain.SysCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-07-13 15:27
 */
@FeignClient(name = "hk-emi")
@RequestMapping("/code")
public interface SysCodeFeignCient {

    /**
     * @param parentId parentId
     * @return
     */
    @GetMapping("/childlist/{parentId}")
    List<SysCode> childListByParentId(@PathVariable("parentId") String parentId);


    /**
     * @param parentCode parentCode
     * @return
     */
    @GetMapping("/childlist/{parentCode}")
    List<SysCode> childListByParentCode(@PathVariable("parentCode") String parentCode);
}
