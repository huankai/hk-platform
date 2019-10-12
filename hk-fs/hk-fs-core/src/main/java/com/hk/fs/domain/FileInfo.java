package com.hk.fs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.commons.util.FileUtils;
import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author kevin
 * @date 2018-08-08 17:31
 */
@Data
@Entity
@Table(name = "fs_file_info")
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
     * 文件组
     */
    @Column(name = "group_name")
    private String groupName;

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

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;

    /**
     * digest
     */
    @Column(name = "digest")
    @JsonIgnore
    private String digest;

    /**
     * 文件扩展名
     */
    @Column(name = "extension")
    private String extension;

    /**
     * 是否为图片
     *
     * @return
     */
    @JsonIgnore
    public boolean isImage() {
        return FileUtils.isImage(fileName);
    }

}
