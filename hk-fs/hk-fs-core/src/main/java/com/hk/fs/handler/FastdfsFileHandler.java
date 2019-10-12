package com.hk.fs.handler;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.hk.commons.util.FileUtils;
import com.hk.commons.util.StringUtils;
import com.hk.fs.properties.FileServer;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangkai
 * @date 2019-3-30 10:00
 */
public class FastdfsFileHandler implements FileHandler {

    private static final String DEFAULT_GROUP = "group1";

    @Setter
    private FileServer fileServer;

    private final AppendFileStorageClient appendFileStorageClient;

    public FastdfsFileHandler(AppendFileStorageClient appendFileStorageClient) {
        this.appendFileStorageClient = appendFileStorageClient;
    }

    @Override
    public FileUploadResponse upload(String bucketName, MultipartFile file) throws IOException {
        StorePath storePath = appendFileStorageClient.uploadAppenderFile(obtainGroupName(bucketName),
                file.getInputStream(), file.getSize(), FileUtils.getExtension(file.getOriginalFilename()));
        return new FileUploadResponse(bucketName, storePath.getPath());
    }

    @Override
    public FileUploadResponse upload(String bucketName, String fileName, byte[] bytes) {
        StorePath storePath = appendFileStorageClient.uploadAppenderFile(obtainGroupName(bucketName),
                new ByteArrayInputStream(bytes), bytes.length, FileUtils.getExtension(fileName));
        return new FileUploadResponse(bucketName, storePath.getPath());
    }

    @Override
    public void deleteByPath(String bucketName, String filePath) {
        appendFileStorageClient.deleteFile(obtainGroupName(bucketName), filePath);
    }

    @Override
    public String getFullPath(String bucketName, String path) {
        bucketName = obtainGroupName(bucketName);
        return String.format("%s/%s/%s", fileServer.getFileUrl(), bucketName, path);
    }

    @Override
    public InputStream getDownInputStream(String bucketName, String path) {
        return appendFileStorageClient.downloadFile(obtainGroupName(bucketName), path, ins -> ins);
    }


    private String obtainGroupName(String group) {
        return StringUtils.defaultIfEmpty(group, DEFAULT_GROUP);
    }
}
