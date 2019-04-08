package com.hk.fs.test;

import java.io.File;
import java.io.IOException;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.hk.commons.util.FileUtils;

/**
 * @author huangkai
 * @date 2019-04-03 22:47
 */
//@SpringBootTest(classes = FSApplication.class)
public class AliOssTest /*extends BaseTest*/ {

//    @Autowired
//    private OSSClient ossClient;

    private static OSSClient ossClient = new OSSClient("http://oss-cn-shenzhen.aliyuncs.com", "LTAIfmx5skZrmV6A", "qPqYn57JJN4xv6O3YukPtFwkJwkBFc");


    public static void main(String[] args) throws IOException {
        try {
//        uploadTest();
            OSSObject object = ossClient.getObject("huangkai2019", "1.png");
            FileUtils.copyInputStreamToFile(object.getObjectContent(), new File("/Users/huangkai/Desktop/2.png"));

        } finally {
            ossClient.shutdown();
        }
    }

    public static void uploadTest() {
        PutObjectResult result = ossClient.putObject("huangkai2019", "1.png", new File("/Users/huangkai/Desktop/1.png"));
        System.out.println(result.getETag());
        System.out.println(result.getRequestId());
//        System.out.println(result.getResponse().getErrorResponseAsString());
//        System.out.println(result.getResponse().getUri());
    }


}
