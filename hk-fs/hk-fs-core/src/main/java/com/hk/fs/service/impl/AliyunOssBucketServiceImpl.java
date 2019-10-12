package com.hk.fs.service.impl;

import com.aliyun.oss.OSSClient;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.exception.ServiceException;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.fs.domain.AliyunOssBucket;
import com.hk.fs.handler.AliyunOssFileHandler;
import com.hk.fs.repository.jpa.AliyunOssBucketRepository;
import com.hk.fs.service.AliyunOssBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huangkai
 * @date 2019-4-4 9:14
 */
@Service
public class AliyunOssBucketServiceImpl extends JpaServiceImpl<AliyunOssBucket, Long> implements AliyunOssBucketService {

    private static Map<String, OSSClient> cacheMap = new ConcurrentHashMap<>();

    private AliyunOssBucketRepository aliyunOssBucketRepository;

    @Autowired
    public AliyunOssBucketServiceImpl(AliyunOssBucketRepository aliyunOssBucketRepository) {
        this.aliyunOssBucketRepository = aliyunOssBucketRepository;
    }

    @Override
    protected BaseJpaRepository<AliyunOssBucket, Long> getBaseRepository() {
        return aliyunOssBucketRepository;
    }

    @Override
    public OSSClient getDefaultOSSClient() {
        OSSClient ossClient = cacheMap.get(AliyunOssFileHandler.DEFAULT_BUCKET_NAME);
        if (null != ossClient) {
            return ossClient;
        }
        throw new ServiceException("不存在默认的 Bucket，请与管理员联系");
    }

    @Override
    public OSSClient getOSSClientByBucketName(String bucketName) {
        OSSClient ossClient = cacheMap.get(bucketName);
        if (ossClient == null) {
            AliyunOssBucket ossBucket = aliyunOssBucketRepository.findByBucketName(bucketName)
                    .orElseThrow(() -> new ServiceException("无此 bucketName:" + bucketName));
            ossClient = buildOSSClient(ossBucket);
            cacheMap.put(bucketName, ossClient);
        }
        return ossClient;
    }

    @Override
    public AliyunOssBucket findByBucketName(String bucketName) {
        return aliyunOssBucketRepository.findByBucketName(bucketName)
                .orElseThrow(() -> new ServiceException("无此 bucketName:" + bucketName));
    }

    @Override
    public AliyunOssBucket createBucket(AliyunOssBucket ossBucket) {
        OSSClient ossClient = getDefaultOSSClient();
        try {
            if (ossClient.doesBucketExist(ossBucket.getBucketName())) {
                throw new ServiceException("bucketName 已存在:" + ossBucket.getBucketName());
            }
            ossClient.createBucket(ossBucket.getBucketName());
            return insert(ossBucket);
        } finally {
            ossClient.shutdown();
        }
    }

    @Override
    public void deleteByBucketName(String bucketName) {
        OSSClient ossClient = getDefaultOSSClient();
        try {
            ossClient.deleteBucket(bucketName);
            aliyunOssBucketRepository.deleteByBucketName(bucketName);
        } finally {
            ossClient.shutdown();
        }
    }

    private OSSClient buildOSSClient(AliyunOssBucket ossBucket) {
        return new OSSClient(ossBucket.getEndPoint(), ossBucket.getAccessKeyId(), ossBucket.getAccessKeySecret());
    }
}
