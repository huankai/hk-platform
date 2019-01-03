package com.hk.fs.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.util.StringUtils;
import com.hk.core.web.Webs;
import com.hk.fs.domain.FileInfo;
import com.hk.fs.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author kevin
 * @date 2018-08-08 17:59
 */
@RestController
@RequestMapping("/file")
public class FileInfoController {

    private final FileInfoService fileInfoService;

    @Autowired
    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    /**
     * 获取文件信息
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping("{id}")
    public JsonResult<FileInfo> get(@PathVariable String id) {
        return JsonResult.success(fileInfoService.getOne(id));
    }

    /**
     * 文件上传
     *
     * @param file file
     * @return JsonResult
     * @throws IOException IOException
     */
    @PostMapping(path = "/upload")
    public JsonResult<?> upload(String group, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return JsonResult.badRequest("文件不能为空");
        }
        FileInfo fileInfo = fileInfoService.uploadFile(group, file.getInputStream(), file.getSize(), file.getOriginalFilename());
        return new JsonResult<>(JsonResult.Status.SUCCESS, "文件上传成功", fileInfo.getId());
    }

    /**
     * 文件下载
     *
     * @param id id
     * @return InputStreamResource
     */
    @GetMapping(path = "/down/{id}")
    public ResponseEntity<InputStreamResource> downFile(@PathVariable String id) {
        FileInfo fileInfo = fileInfoService.getOne(id);
        return Webs.toDownloadResponseEntity(fileInfo.getFileName(), StringUtils.createResource(
                fileInfoService.getFullPath(fileInfo.getGroupName(), fileInfo.getFilePath())));
    }

    /**
     * 图片预览
     *
     * @param id id
     * @return ResponseEntity
     */
    @GetMapping(path = "/view/{id}")
    public ResponseEntity<InputStreamResource> viewImage(@PathVariable String id) {
        FileInfo fileInfo = fileInfoService.getOne(id);
        return Webs.toImageViewResponseEntity(StringUtils.createResource(
                fileInfoService.getFullPath(fileInfo.getGroupName(), fileInfo.getFilePath())));
    }

    /**
     * 只删除文件记录，不真实删除文件
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id}")
    public JsonResult<Void> deleteFile(@PathVariable String id) {
        fileInfoService.deleteById(id);
        return JsonResult.success();
    }
}
