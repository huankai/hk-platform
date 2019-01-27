package com.hk.message.web.dto;

import java.util.Collection;

import com.hk.message.web.domain.Message;
import com.hk.message.web.domain.MessageAttachment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * 消息附件信息
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("serial")
public class MessageAttachmentDTO extends Message {

    /**
     * 消息附件
     */
    private Collection<MessageAttachment> attachments;

    public MessageAttachmentDTO(Message message, Collection<MessageAttachment> attachments) {
        setId(message.getId());
        setTitle(message.getTitle());
        setMsgContent(message.getMsgContent());
        setSenderBy(message.getSenderBy());
        setSenderName(message.getSenderName());
        setSenderDate(message.getSenderDate());
        setSenderTo(message.getSenderTo());
        this.attachments = attachments;
    }
}
