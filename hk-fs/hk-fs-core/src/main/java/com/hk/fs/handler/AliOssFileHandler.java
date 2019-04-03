package com.hk.fs.handler;

import com.aliyun.oss.OSSClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author huangkai
 * @date 2019-04-03 22:18
 */
public class AliOssFileHandler implements FileHandler {

    private OSSClient ossClient;

    public AliOssFileHandler(OSSClient ossClient) {
        this.ossClient = ossClient;
    }

    @Override
    public String upload(String groupName, InputStream stream, long size, String ext) throws IOException {
        return null;
    }

    @Override
    public void deleteByPath(String groupName, String filePath) {

    }

    @Override
    public String getFullPath(String groupName, String path) {
        return null;
    }
}
