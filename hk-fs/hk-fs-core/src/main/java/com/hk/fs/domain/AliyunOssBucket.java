package com.hk.fs.domain;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author huangkai
 * @date 2019-4-4 9:10
 */
@Data
@Entity
@Table(name = "aliyun_oss_bucket")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AliyunOssBucket extends AbstractAuditable {

    @Column(name = "bucket_name")
    private String bucketName;

    @Column(name = "end_point")
    private String endPoint;

    @Column(name = "bucket_host")
    private String bucketHost;

    @Column(name = "custom_host")
    private String customHost;

    @Column(name = "access_key_id")
    private String accessKeyId;

    @Column(name = "access_key_secret")
    private String accessKeySecret;
}
