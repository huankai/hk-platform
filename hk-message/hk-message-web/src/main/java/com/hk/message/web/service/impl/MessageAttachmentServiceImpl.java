package com.hk.message.web.service.impl;

import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.message.web.domain.MessageAttachment;
import com.hk.message.web.repository.jpa.MessageAttachmentRepository;
import com.hk.message.web.service.MessageAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class MessageAttachmentServiceImpl extends JpaServiceImpl<MessageAttachment, Long> implements MessageAttachmentService {

    @Autowired
    private MessageAttachmentRepository messageAttachmentRepository;

    @Override
    protected BaseJpaRepository<MessageAttachment, Long> getBaseRepository() {
        return messageAttachmentRepository;
    }

    @Override
    public Collection<MessageAttachment> findByMessageId(Long messageId) {
        return messageAttachmentRepository.findByMessageId(messageId);
    }

    @Override
    public Iterable<MessageAttachment> batchInsertMessageAttachment(Long messageId, Collection<MessageAttachment> attachments) {
        if (CollectionUtils.isNotEmpty(attachments)) {
            attachments.forEach(item -> item.setMessageId(messageId));
            return batchInsert(attachments);
        }
        return Collections.emptyList();
    }
}
