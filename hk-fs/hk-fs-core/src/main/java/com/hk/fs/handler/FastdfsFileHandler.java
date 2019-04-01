package com.hk.fs.handler;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.hk.commons.util.StringUtils;
import com.hk.fs.properties.FileServer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * @author huangkai
 * @date 2019-3-30 10:00
 */
public class FastdfsFileHandler implements FileHandler {

    private static final String DEFAULT_GROUP = "group1";

    @Autowired
    private FileServer fileServer;

    private final AppendFileStorageClient appendFileStorageClient;

    @Autowired
    public FastdfsFileHandler(AppendFileStorageClient appendFileStorageClient) {
        this.appendFileStorageClient = appendFileStorageClient;
    }

    @Override
    public String upload(String groupName, InputStream stream, long size, String ext) {
        StorePath storePath = appendFileStorageClient.uploadAppenderFile(groupName, stream, size, ext);
        return storePath.getPath();
    }

    @Override
    public void deleteByPath(String groupName, String filePath) {
        appendFileStorageClient.deleteFile(groupName, filePath);
    }

    @Override
    public String getFullPath(String groupName, String path) {
        if (StringUtils.isEmpty(groupName)) {
            groupName = DEFAULT_GROUP;
        }
        return String.format("%s/%s/%s", fileServer.getFileUrl(), groupName, path);
    }
}
