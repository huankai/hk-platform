package com.hk.fs.config;

import com.aliyun.oss.OSSClient;
import com.hk.fs.properties.AliOSSProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-04-03 22:43
 */
@Configuration
@EnableConfigurationProperties(value = {AliOSSProperties.class})
public class AliOssConfiguration {

    private AliOSSProperties aliOSSProperties;

    public AliOssConfiguration(AliOSSProperties aliOSSProperties) {
        this.aliOSSProperties = aliOSSProperties;
    }

    @Bean
    public OSSClient ossClient() {
        List<AliOSSProperties.Bucket> bucketList = aliOSSProperties.getBucketList();
        AliOSSProperties.Bucket bucket = bucketList.get(0);
        return new OSSClient(bucket.getEndpoint(),
                bucket.getAccessKeyId(), bucket.getAccessKeySecret());
    }
}
