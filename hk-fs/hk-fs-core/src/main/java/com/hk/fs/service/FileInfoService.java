package com.hk.fs.service;


import com.hk.commons.util.AssertUtils;
import com.hk.core.service.jpa.JpaBaseService;
import com.hk.fs.domain.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author kevin
 * @date 2018-08-08 17:46
 */
public interface FileInfoService extends JpaBaseService<FileInfo, Long> {

    String B_UNIT = "B";

    String KB_UNIT = "KB";

    String MB_UNIT = "MB";

    String GB_UNIT = "GB";

    BigDecimal B_MAX_SIZE = new BigDecimal(1024);

    BigDecimal KB_MAX_SIZE = B_MAX_SIZE.pow(2);

    BigDecimal MB_MAX_SIZE = B_MAX_SIZE.pow(3);

    BigDecimal GB_MAX_SIZE = B_MAX_SIZE.pow(4);

    /**
     * 多上传文件
     *
     * @param group        文件所属组
     * @param multiFileMap 文件
     * @return {@link FileInfo}
     */
    List<FileInfo> uploadFile(String group, MultiValueMap<String, MultipartFile> multiFileMap) throws IOException;

    /**
     * 获取文件全路径
     *
     * @param groupName groupName
     * @param path      filePath
     * @return fullPath
     */
    String getFullPath(String groupName, String path);

    /**
     * @param fileSize fileSize
     * @return FileSizeUnit
     */
    static FileSizeUnit convertFileSize(long fileSize) {
        AssertUtils.isTrue(fileSize > 0, "文件大小不能小于0");
        if (fileSize < B_MAX_SIZE.longValue()) {
            return new FileSizeUnit(Double.valueOf(String.valueOf(fileSize)), B_UNIT);
        } else if (fileSize < KB_MAX_SIZE.longValue()) {
            return new FileSizeUnit(new BigDecimal(fileSize).divide(B_MAX_SIZE, 2, BigDecimal.ROUND_DOWN).doubleValue(), KB_UNIT);
        } else if (fileSize < MB_MAX_SIZE.longValue()) {
            return new FileSizeUnit(new BigDecimal(fileSize).divide(KB_MAX_SIZE, 2, BigDecimal.ROUND_DOWN).doubleValue(), MB_UNIT);
        } else if (fileSize < GB_MAX_SIZE.longValue()) {
            return new FileSizeUnit(new BigDecimal(fileSize).divide(MB_MAX_SIZE, 2, BigDecimal.ROUND_DOWN).doubleValue(), GB_UNIT);
        }
        throw new IllegalArgumentException("文件过大！");
    }

    @AllArgsConstructor
    class FileSizeUnit {

        @Getter
        private double fileSize;

        @Getter
        private String unit;
    }
}
