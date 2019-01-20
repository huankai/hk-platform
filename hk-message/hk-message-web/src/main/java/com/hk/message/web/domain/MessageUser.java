package com.hk.message.web.domain;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "t_message_user")
public class MessageUser extends AbstractAuditable {

    @Column(value = "message_id")
    private String messageId;

    @Column(value = "user_id")
    private String userId;

    @Column(value = "read_state")
    private Byte readState;

    @Column(value = "read_date")
    private LocalDateTime readDate;
}
