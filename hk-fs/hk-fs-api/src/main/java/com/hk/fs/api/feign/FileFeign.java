package com.hk.fs.api.feign;

import com.hk.fs.api.domain.FileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: sjq-278
 * @date: 2018-09-13 13:34
 */
@FeignClient(name = "hk-fs")
@RequestMapping("/api/file")
public interface FileFeign {

    @GetMapping("{id}")
    FileInfo get(@PathVariable("id") String id);

    @GetMapping("server-url")
    String getFileServerUrl();
}
