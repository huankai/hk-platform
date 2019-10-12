package com.hk.fs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 文件分享
 *
 * @author kevin
 * @date 2018-08-08 17:35
 */
@Data
@Entity
@Table(name = "fs_file_share")
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class FileShare extends AbstractAuditable {

    /**
     * 文件Id
     */
    @Column(name = "file_id")
    private String fileId;

    /**
     * 分享地址
     */
    @Column(name = "share_uri")
    private String shareUri;

    /**
     * 分享的密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 分享时间
     */
    @Column(name = "share_date")
    private LocalDateTime shareDate;

    /**
     * 过期时间
     */
    @Column(name = "expire")
    private LocalDateTime expire;

    /**
     * 分享是否已过期
     *
     * @return
     */
    @JsonIgnore
    public boolean isExpire() {
        return null != expire && LocalDateTime.now().isAfter(expire);
    }

    /**
     * 是否加密分享
     *
     * @return
     */
    @JsonIgnore
    public boolean isSecurityShare() {
        return StringUtils.isNotEmpty(password);
    }
}
