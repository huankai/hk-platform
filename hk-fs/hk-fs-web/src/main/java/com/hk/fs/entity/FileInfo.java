package com.hk.fs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hk.core.data.jpa.domain.AbstractAuditable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: kevin
 * @date 2018-08-08 17:31
 */
@Data
@Entity
@Table(name = "file_info")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class FileInfo extends AbstractAuditable {

    /**
     * 文件所有者
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private Double fileSize;

    /**
     * 文件大小单位
     */
    @Column(name = "unit")
    private String unit;

    /**
     * 文件扩展名
     */
    @Column(name = "extension")
    private String extension;

}
