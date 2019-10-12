package com.hk.fs.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: kevin
 * @date: 2018-09-13 13:35
 */
@Data
public class FileInfo implements Serializable {

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
