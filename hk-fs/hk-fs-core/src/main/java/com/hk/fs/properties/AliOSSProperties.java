package com.hk.fs.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkai
 * @date 2019-04-03 22:24
 */
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOSSProperties {

    private List<Bucket> bucketList = new ArrayList<>();

    @Getter
    @Setter
    public static class Bucket {

        private String bucketName = "huangkai2019";

        private String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";

        private String accessKeyId = "LTAIfmx5skZrmV6A";

        private String accessKeySecret = "qPqYn57JJN4xv6O3YukPtFwkJwkBFc";

        private String host;
    }
}
