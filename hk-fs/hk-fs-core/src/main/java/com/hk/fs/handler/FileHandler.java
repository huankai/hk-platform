package com.hk.fs.handler;

import com.hk.commons.util.DateIdGenerator;
import com.hk.commons.util.FileUtils;
import com.hk.commons.util.IDGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangkai
 * @date 2019-3-28 12:35
 */
public interface FileHandler {

    /**
     * 上传文件
     *
     * @param bucketName bucketName
     * @param file       file
     * @return {@link FileUploadResponse}
     * @throws IOException
     */
    FileUploadResponse upload(String bucketName, MultipartFile file) throws IOException;

    /**
     * 上传文件
     *
     * @param bucketName bucketName
     * @param fileName   fileName
     * @param bytes      bytes
     * @return {@link FileUploadResponse}
     * @throws IOException
     */
    FileUploadResponse upload(String bucketName, String fileName, byte[] bytes) throws IOException;

    /**
     * 删除文件
     *
     * @param bucketName bucketName
     * @param filePath   filePath
     */
    void deleteByPath(String bucketName, String filePath);

    /**
     * 获取文件全路径
     *
     * @param bucketName bucketName
     * @param path       path
     * @return 文件全路径
     */
    String getFullPath(String bucketName, String path);

    /**
     * 下载文件
     *
     * @param bucketName bucketName
     * @param path       path
     * @return 文件流
     * @throws IOException
     */
    InputStream getDownInputStream(String bucketName, String path) throws IOException;

    /**
     * 生成日期目录格式的文件名
     *
     * @return yyyy/MM/dd/fileName
     */
    static String generateDateFileName(String prefix, String fileName) {
        return String.format("%s/%s", new DateIdGenerator(prefix, null).generate(), generateUUIDFileName(fileName));
    }

    static String generateUUIDFileName(String fileName) {
        return String.format("%s.%s", IDGenerator.STRING_UUID.generate(), FileUtils.getExtension(fileName));
    }
}
