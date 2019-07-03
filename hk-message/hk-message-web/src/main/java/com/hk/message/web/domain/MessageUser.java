package com.hk.message.web.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_message_user")
@SuppressWarnings("serial")
public class MessageUser extends AbstractSnowflakeAuditable {

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "read_date")
    private LocalDateTime readDate;
}
