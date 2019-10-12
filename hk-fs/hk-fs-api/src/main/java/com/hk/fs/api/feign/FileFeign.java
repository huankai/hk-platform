package com.hk.fs.api.feign;

import com.hk.fs.api.domain.FileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @author: kevin
 * @date: 2018-09-13 13:34
 */
@FeignClient(name = "hk-fs")
@RequestMapping("/api/file")
public interface FileFeign {

    @GetMapping("{id}")
    FileInfo get(@PathVariable("id") String id);

    @GetMapping("server-url")
    String getFileServerUrl();

    /**
     * 上传文件
     *
     * @param file file
     * @return
     */
    @PostMapping("upload/file")
    FileInfo uploadFile(File file);

    /**
     * 上传文件
     *
     * @param in in
     * @return
     */
    @PostMapping("upload/inputStream")
    FileInfo uploadFile(InputStream in);

    /**
     * 上传文件
     *
     * @param file file
     * @return
     */
    @PostMapping("upload/multipart")
    FileInfo uploadFile(@RequestParam("file") MultipartFile file);
}
