package com.hk.fs.api.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author huangkai
 * @date 2019-4-1 15:00
 */
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("serial")
public class FileInfoResponse implements Serializable {

    private String id;

    /**
     * 文件所有者
     */
    private String userId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Double fileSize;

    /**
     * 文件大小单位
     */
    private String unit;

    /**
     * 文件扩展名
     */
    private String extension;
}
