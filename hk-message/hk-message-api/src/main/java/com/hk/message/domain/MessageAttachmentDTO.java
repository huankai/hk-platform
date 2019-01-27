package com.hk.message.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("serial")
public class MessageAttachmentDTO extends Message {

    /**
     * 消息附件信息
     */
    private List<MessageAttachment> attachments;
}
