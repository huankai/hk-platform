package com.hk.fs.api.feign.fallback;

import com.hk.fs.api.feign.FileFeign;
import com.hk.fs.api.response.FileInfoResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * 当服务不可用时， 才会调用此方法中的服务，返回自定义的异常信息
 *
 * @author huangkai
 * @date 2019-4-1 15:07
 */
@Component
public class FileFallbackFactory implements FallbackFactory<FileFeign> {

    @Override
    public FileFeign create(Throwable cause) {
        return new FileFeign() {
            @Override
            public FileInfoResponse get(String id) {
                return null;
            }

            @Override
            public String getFileServerUrl() {
                return "";
            }

            @Override
            public FileInfoResponse uploadFile(File file) {
                return null;
            }

            @Override
            public FileInfoResponse uploadFile(InputStream in) {
                return null;
            }

            @Override
            public List<FileInfoResponse> uploadFile(MultipartFile file) {
                return Collections.emptyList();
            }
        };
    }
}
