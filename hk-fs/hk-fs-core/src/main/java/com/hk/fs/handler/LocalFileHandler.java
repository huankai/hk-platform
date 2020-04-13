package com.hk.fs.handler;

import com.hk.commons.util.FileUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.commons.util.StringUtils;
import com.hk.fs.properties.FileServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
@RequiredArgsConstructor
public class LocalFileHandler implements FileHandler {

    private final String basePath;

    private final FileServer fileServer;

    @Override
    public FileUploadResponse upload(String bucketName, MultipartFile file) throws IOException {
        bucketName = getBucketOrDefault(bucketName);
        String path = FileHandler.generateDateFileName(null, file.getName());
        File dist = new File(String.format("%s/%s/%s", basePath, bucketName, path));
        log.debug("upload file:{}", dist.getPath());
        FileUtils.copyToFile(file.getInputStream(), dist);
        return new FileUploadResponse(bucketName, path);
    }

    @Override
    public FileUploadResponse upload(String bucketName, String fileName, byte[] bytes) throws IOException {
        bucketName = getBucketOrDefault(bucketName);
        String path = FileHandler.generateDateFileName(null, fileName);
        File dist = new File(String.format("%s/%s/%s", basePath, bucketName, path));
        log.debug("upload file:{}", dist.getPath());
        FileUtils.copyToFile(new ByteArrayInputStream(bytes), dist);
        return new FileUploadResponse(bucketName, path);
    }

    @Override
    public boolean deleteByPath(String bucketName, String filePath) {
        bucketName = getBucketOrDefault(bucketName);
        File file = new File(String.format("%s/%s/%s", basePath, bucketName, replaceParentDir(filePath)));
        boolean result = file.isFile() && file.exists() && file.delete();
        if (result) {
            log.debug("Deleted File:{}", file.getPath());
        }
        return result;
    }

    private String getBucketOrDefault(String bucketName) {
        return StringUtils.defaultIfEmpty(bucketName, "./");
    }

    private String replaceParentDir(String filePath) {
        return StringUtils.replace(filePath, "../", "");
    }

    @Override
    public String getFullPath(String bucketName, String path) {
        bucketName = getBucketOrDefault(bucketName);
        return String.format("%s/%s/%s", fileServer.getFileUrl(), bucketName, replaceParentDir(path));
    }

    @Override
    public InputStream getDownInputStream(String bucketName, String path) throws IOException {
        bucketName = getBucketOrDefault(bucketName);
        File file = new File(String.format("%s/%s/%s", basePath, bucketName, replaceParentDir(path)));
        return FileUtils.openInputStream(file);
    }
}
