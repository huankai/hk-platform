package com.hk.message.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.message.web.domain.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * <pre>
 * 用户消息DTO
 *  每个用户对应一条消息
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("serial")
public class MessageUserDTO extends Message {

    /**
     * 用户Id
     */
    @JsonIgnore
    private Long userId;

    /**
     * 消息读取状态
     */
    private Boolean isRead;

    /**
     * 消息读取时间
     */
    private LocalDateTime readDate;

    public MessageUserDTO(Message message, Boolean isRead, LocalDateTime readDate) {
        setId(message.getId());
        setTitle(message.getTitle());
        setMsgContent(message.getMsgContent());
        setSenderBy(message.getSenderBy());
        setSenderName(message.getSenderName());
        setSenderDate(message.getSenderDate());
        setSenderTo(message.getSenderTo());
        this.isRead = isRead;
        this.readDate = readDate;
    }
}
