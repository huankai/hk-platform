package com.hk.fs.service;


import com.hk.commons.util.AssertUtils;
import com.hk.core.service.BaseService;
import com.hk.fs.domain.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.InputStream;
import java.math.BigDecimal;

/**
 * @author: kevin
 * @date 2018-08-08 17:46
 */
public interface FileInfoService extends BaseService<FileInfo, String> {

    String B_UNIT = "B";

    String KB_UNIT = "KB";

    String MB_UNIT = "MB";

    String GB_UNIT = "GB";

    BigDecimal B_MAX_SIZE = new BigDecimal(1024);

    BigDecimal KB_MAX_SIZE = B_MAX_SIZE.pow(2);

    BigDecimal MB_MAX_SIZE = B_MAX_SIZE.pow(3);

    BigDecimal GB_MAX_SIZE = B_MAX_SIZE.pow(4);

    FileInfo uploadFile(String group, InputStream in, long fileSize, String fileName);

    /**
     * @param fileSize fileSize
     * @return FileSizeUnit
     */
    static FileSizeUnit convertFileSize(long fileSize) {
        AssertUtils.isTrue(fileSize > 0, "文件大小不能小于0");
        double convertFileSize;
        String unit;
        if (fileSize < B_MAX_SIZE.longValue()) {
            convertFileSize = Double.valueOf(String.valueOf(fileSize));
            unit = B_UNIT;
        } else if (fileSize < KB_MAX_SIZE.longValue()) {
            convertFileSize = new BigDecimal(fileSize).divide(B_MAX_SIZE, 2, BigDecimal.ROUND_DOWN).doubleValue();
            unit = KB_UNIT;
        } else if (fileSize < MB_MAX_SIZE.longValue()) {
            convertFileSize = new BigDecimal(fileSize).divide(KB_MAX_SIZE, 2, BigDecimal.ROUND_DOWN).doubleValue();
            unit = MB_UNIT;
        } else if (fileSize < GB_MAX_SIZE.longValue()) {
            convertFileSize = new BigDecimal(fileSize).divide(MB_MAX_SIZE, 2, BigDecimal.ROUND_DOWN).doubleValue();
            unit = GB_UNIT;
        } else {
            throw new IllegalArgumentException("文件过大！");
        }
        return new FileSizeUnit(convertFileSize, unit);
    }

    @AllArgsConstructor
    class FileSizeUnit {

        @Getter
        private double fileSize;

        @Getter
        private String unit;
    }
}
