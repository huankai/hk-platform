package com.hk.fs.service;

import com.aliyun.oss.OSSClient;
import com.hk.core.service.jpa.JpaBaseService;
import com.hk.fs.domain.AliyunOssBucket;

/**
 * @author huangkai
 * @date 2019-4-4 9:13
 */
public interface AliyunOssBucketService extends JpaBaseService<AliyunOssBucket, Long> {

    OSSClient getDefaultOSSClient();

    OSSClient getOSSClientByBucketName(String bucketName);

    AliyunOssBucket findByBucketName(String bucketName);

    AliyunOssBucket createBucket(AliyunOssBucket ossBucket);

    void deleteByBucketName(String bucketName);
}
