package com.hk.message.web.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "t_message")
public class Message extends AbstractAuditable {

    @Column(value = "title")
    private String title;

    @Column(value = "msg_content")
    private String msgContent;

    @Column(value = "sender_by")
    private String senderBy;

    @Column(value = "sender_name")
    private String senderName;

    @Column(value = "sender_date")
    private LocalDateTime senderDate;

    @Column(value = "sender_to")
    private String senderTo;
}
