package com.hk.message.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("serial")
public class MessageUserDTO extends Message {

    /**
     * 消息读取状态
     */
    private Boolean isRead;

    /**
     * 消息读取时间
     */
    private LocalDateTime readDate;
}
