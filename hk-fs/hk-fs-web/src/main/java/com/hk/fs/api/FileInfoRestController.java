package com.hk.fs.api;

import com.hk.fs.domain.FileInfo;
import com.hk.fs.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sjq-278
 * @date: 2018-09-13 13:31
 */
@RestController
@RequestMapping("/api/file")
public class FileInfoRestController {

    @Autowired
    private FileInfoService fileInfoService;

    @GetMapping("{id}")
    public FileInfo get(@PathVariable String id) {
        return fileInfoService.getOne(id);
    }
}
