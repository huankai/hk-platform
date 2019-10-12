package com.hk.message.web.service.impl;

import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.message.web.domain.MessageAttachment;
import com.hk.message.web.repository.jdbc.MessageAttachmentRepository;
import com.hk.message.web.service.MessageAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class MessageAttachmentServiceImpl extends JdbcServiceImpl<MessageAttachment, String> implements MessageAttachmentService {

    @Autowired
    private MessageAttachmentRepository messageAttachmentRepository;

    @Override
    protected JdbcRepository<MessageAttachment, String> getBaseRepository() {
        return messageAttachmentRepository;
    }

    @Override
    public Collection<MessageAttachment> findByMessageId(String messageId) {
        return messageAttachmentRepository.findByMessageId(messageId);
    }

    @Override
    public Iterable<MessageAttachment> batchInsertMessageAttachment(String messageId, Collection<MessageAttachment> attachments) {
        if (CollectionUtils.isNotEmpty(attachments)) {
            attachments.forEach(item -> item.setMessageId(messageId));
            return batchInsert(attachments);
        }
        return Collections.emptyList();
    }
}
