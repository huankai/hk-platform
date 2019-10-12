package com.hk.fs.api;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.fs.domain.FileInfo;
import com.hk.fs.properties.FileServer;
import com.hk.fs.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * @author kevin
 * @date 2018-09-13 13:31
 */
@RestController
@RequestMapping("/api/file")
public class FileInfoRestController {

    @Autowired
    private FileInfoService fileInfoService;

    @Autowired
    private FileServer fileServer;

    @GetMapping(path = "{id}")
    public FileInfo get(@PathVariable String id) {
        return fileInfoService.getOne(id);
    }

    @GetMapping(path = "/server-url")
    public String getServerUrl() {
        return fileServer.getFileUrl();
    }

    @PostMapping(path = "/list")
    public QueryPage<FileInfo> queryPage(QueryModel<FileInfo> queryModel) {
        return fileInfoService.queryForPage(queryModel);
    }

    /**
     * 上传文件
     *
     * @param groupName 组名
     * @param request   文件
     * @return {@link FileInfo}
     * @throws IOException
     */
    @PostMapping(path = "/upload/multipart")
    public List<FileInfo> upload(@RequestParam(value = "group", required = false) String groupName,
                                 MultipartHttpServletRequest request) throws IOException {
        return fileInfoService.uploadFile(groupName, request.getMultiFileMap());
    }
}
