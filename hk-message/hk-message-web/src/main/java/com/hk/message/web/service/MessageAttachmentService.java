package com.hk.message.web.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.message.web.domain.MessageAttachment;

import java.util.Collection;

public interface MessageAttachmentService extends JdbcBaseService<MessageAttachment, String> {

    /**
     * 获取消息附件信息
     *
     * @param messageId 消息id
     * @return {@link MessageAttachment}
     */
    Collection<MessageAttachment> findByMessageId(String messageId);

    /**
     * 批量保存消息附件
     *
     * @param messageId   messageId
     * @param attachments attachments
     * @return {@link MessageAttachment}
     */
    Iterable<MessageAttachment> batchInsertMessageAttachment(String messageId, Collection<MessageAttachment> attachments);
}
