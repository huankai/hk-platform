package com.hk.message.web.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.message.web.domain.MessageAttachment;

import java.util.Collection;

public interface MessageAttachmentRepository extends LongIdJpaRepository<MessageAttachment> {

//    @Query(value = "SELECT id,message_id,attachment_id,attachment_path,attachment_name " +
//            "FROM t_message_attachment WHERE message_id = :messageId")
    Collection<MessageAttachment> findByMessageId(Long messageId);
}
