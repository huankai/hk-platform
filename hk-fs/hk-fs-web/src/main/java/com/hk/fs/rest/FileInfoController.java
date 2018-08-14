package com.hk.fs.rest;

import com.hk.core.web.JsonResult;
import com.hk.fs.entity.FileInfo;
import com.hk.fs.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date 2018-08-08 17:59
 */
@RestController
@RequestMapping("/file")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    @GetMapping("{id}")
    public JsonResult get(@PathVariable String id) {
        FileInfo fileInfo = fileInfoService.getOne(id);
        return JsonResult.success(fileInfo);
    }


}
