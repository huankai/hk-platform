package com.hk.message.web.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_message_attachment")
@SuppressWarnings("serial")
public class MessageAttachment extends AbstractSnowflakeAuditable {

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "attachment_id")
    private Long attachmentId;

    @Column(name = "attachment_path")
    private String attachmentPath;

    @Column(name = "attachment_name")
    private String attachmentName;
}
