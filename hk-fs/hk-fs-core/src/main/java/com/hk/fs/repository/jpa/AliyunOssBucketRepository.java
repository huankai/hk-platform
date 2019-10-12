package com.hk.fs.repository.jpa;


import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.fs.domain.AliyunOssBucket;

import java.util.Optional;

/**
 * @author huangkai
 * @date 2019-4-4 9:13
 */
public interface AliyunOssBucketRepository extends LongIdJpaRepository<AliyunOssBucket> {

    Optional<AliyunOssBucket> findByBucketName(String bucketName);

    void deleteByBucketName(String bucketName);
}
