package com.hk.fs.api;

import com.hk.fs.config.FileServer;
import com.hk.fs.domain.FileInfo;
import com.hk.fs.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: sjq-278
 * @date: 2018-09-13 13:31
 */
@RestController
@RequestMapping("/api/file")
public class FileInfoRestController {

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private FileServer fileServer;

    @GetMapping("{id}")
    public FileInfo get(@PathVariable String id) {
        return fileInfoService.getOne(id);
    }

    @GetMapping("/server-url")
    public String getServerUrl() {
        return fileServer.getFileUrl();
    }

    @PostMapping("/upload/multipart")
    public FileInfo upload(@RequestParam("file") MultipartFile file) {
        return null;
    }
}
