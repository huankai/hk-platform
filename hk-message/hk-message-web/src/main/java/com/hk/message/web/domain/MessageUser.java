package com.hk.message.web.domain;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.hk.core.data.jdbc.domain.AbstractAuditable;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "t_message_user")
@SuppressWarnings("serial")
public class MessageUser extends AbstractAuditable {

    @Column(value = "message_id")
    private String messageId;

    @Column(value = "user_id")
    private String userId;

    @Column(value = "is_read")
    private Boolean isRead;

    @Column(value = "read_date")
    private LocalDateTime readDate;
}
