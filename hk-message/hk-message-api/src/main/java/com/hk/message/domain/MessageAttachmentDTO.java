package com.hk.message.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MessageAttachmentDTO extends Message {

    /**
     * 消息附件信息
     */
    private List<MessageAttachment> attachments;
}
