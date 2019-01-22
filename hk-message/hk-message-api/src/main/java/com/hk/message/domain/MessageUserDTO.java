package com.hk.message.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
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
