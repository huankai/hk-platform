package com.hk.fs.controller;

import com.hk.core.web.Webs;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author huangkai
 * @date 2019-01-03 08:56
 */
@Controller
@RequestMapping(path = "/excel/template")
public class ExcelTemplateController {


    /**
     * Excel 模板文件下载
     *
     * @param name 模板文件名
     * @return
     */
    @RequestMapping(path = "/down")
    public ResponseEntity<InputStreamResource> excelTemplate(@RequestParam("name") String name) {
        return Webs.toDownloadResponseEntity(name, (byte[]) null);

    }
}
