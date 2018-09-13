package com.hk.fs.controller;

import com.hk.core.web.JsonResult;
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
 * @author: kevin
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
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public JsonResult get(@PathVariable String id) {
        FileInfo fileInfo = fileInfoService.getOne(id);
        return JsonResult.success(fileInfo);
    }

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(path = "/upload")
    public JsonResult upload(String group, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return JsonResult.badRequest("文件不能为空");
        }
        FileInfo fileInfo = fileInfoService.uploadFile(group, file.getInputStream(), file.getSize(), file.getOriginalFilename());
        return new JsonResult(JsonResult.Status.SUCCESS, "文件上传成功", fileInfo.getId());
    }

    /**
     * 文件下载
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/down/{id}")
    public ResponseEntity<InputStreamResource> downFile(@PathVariable String id) throws IOException {
        FileInfo fileInfo = fileInfoService.getOne(id);
        return Webs.toDownloadResponseEntity(fileInfo.getFileName(), fileInfo.getResource());
    }

    /**
     * 图片预览
     *
     * @param id id
     * @return ResponseEntity
     */
    @GetMapping(path = "/view/{id}")
    public ResponseEntity<InputStreamResource> viewImage(@PathVariable String id) throws IOException {
        FileInfo fileInfo = fileInfoService.getOne(id);
        return Webs.toImageViewResponseEntity(fileInfo.getResource());
    }

    /**
     * 只删除文件记录，不真实删除文件
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id}")
    public JsonResult deleteFile(@PathVariable String id) {
        fileInfoService.deleteById(id);
        return JsonResult.success();
    }
}
