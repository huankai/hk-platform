package com.hk.fs.handler;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.hk.commons.util.StringUtils;
import com.hk.fs.domain.AliyunOssBucket;
import com.hk.fs.service.AliyunOssBucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangkai
 * @date 2019-04-03 22:18
 */
@RequiredArgsConstructor
public class AliyunOssFileHandler implements FileHandler {

    public static final String DEFAULT_BUCKET_NAME = "fengniaoxls-oss";

    private final AliyunOssBucketService aliyunOssBucketService;

    @Override
    public FileUploadResponse upload(String bucketName, MultipartFile file) throws IOException {
        bucketName = obtainBucketName(bucketName);
        OSSClient ossClient = aliyunOssBucketService.getOSSClientByBucketName(bucketName);
        try {
            String filePath = FileHandler.generateDateFileName(null, file.getOriginalFilename());
            ossClient.putObject(bucketName, filePath,
                    file.getInputStream());
            return new FileUploadResponse(bucketName, filePath);
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public FileUploadResponse upload(String bucketName, String fileName, byte[] bytes) {
        bucketName = obtainBucketName(bucketName);
        OSSClient ossClient = aliyunOssBucketService.getOSSClientByBucketName(bucketName);
        try {
            String filePath = FileHandler.generateDateFileName(null, fileName);
            ossClient.putObject(bucketName, filePath, new ByteArrayInputStream(bytes));
            return new FileUploadResponse(bucketName, filePath);
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public void deleteByPath(String bucketName, String filePath) {
        OSSClient ossClient = aliyunOssBucketService.getOSSClientByBucketName(bucketName);
        try {
            ossClient.deleteObject(obtainBucketName(bucketName), filePath);
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public String getFullPath(String bucketName, String path) {
        bucketName = obtainBucketName(bucketName);
        AliyunOssBucket bucket = aliyunOssBucketService.findByBucketName(bucketName);
        return String.format("%s/%s", StringUtils.defaultIfEmpty(bucket.getCustomHost(),
                bucket.getBucketHost()), path);
    }

    @Override
    public InputStream getDownInputStream(String bucketName, String path) {
        bucketName = obtainBucketName(bucketName);
        OSSClient ossClient = aliyunOssBucketService.getOSSClientByBucketName(bucketName);
        OSSObject object = ossClient.getObject(bucketName, path);
        return object.getObjectContent();
    }

    private String obtainBucketName(String bucketName) {
        return StringUtils.defaultIfEmpty(bucketName, DEFAULT_BUCKET_NAME);
    }
}
