package com.hk.fs.api.feign;

import com.hk.fs.api.feign.configuration.FileConfiguration;
import com.hk.fs.api.feign.fallback.FileFallbackFactory;
import com.hk.fs.api.response.FileInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * <pre>
 *
 * name : 调用的服务名称
 * path : 调用的服务上下文路径
 * configuration: 配置文件，如日志、等其它配置
 * fallbackFactory: 断路器，将服务不可用时才调用
 *
 * </pre>
 *
 * @author kevin
 * @date 2018-09-13 13:34
 */
@FeignClient(name = FileService.SERVICE_NAME,
        path = FileService.CONTEXT_PATH,
        configuration = FileConfiguration.class,
        fallbackFactory = FileFallbackFactory.class)
@RequestMapping("/api/file")
public interface FileFeign {

    @GetMapping("{id}")
    FileInfoResponse get(@PathVariable("id") Long id);

    @GetMapping("server-url")
    String getFileServerUrl();

    /**
     * 上传文件
     *
     * @param file file
     * @return
     */
    @PostMapping("upload/file")
    FileInfoResponse uploadFile(File file);

    /**
     * 上传文件
     *
     * @param in in
     * @return
     */
    @PostMapping("upload/inputStream")
    FileInfoResponse uploadFile(InputStream in);

    /**
     * 上传文件
     *
     * @param file file
     * @return
     */
    @PostMapping("upload/multipart")
    List<FileInfoResponse> uploadFile(@RequestParam("file") MultipartFile file);
}
