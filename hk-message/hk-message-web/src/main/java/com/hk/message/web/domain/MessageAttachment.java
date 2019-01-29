package com.hk.message.web.domain;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.hk.core.data.jdbc.domain.AbstractAuditable;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "t_message_attachment")
@SuppressWarnings("serial")
public class MessageAttachment extends AbstractAuditable {

    @Column(value = "message_id")
    private String messageId;

    @Column(value = "attachment_id")
    private String attachmentId;

    @Column(value = "attachment_path")
    private String attachmentPath;

    @Column(value = "attachment_name")
    private String attachmentName;
}