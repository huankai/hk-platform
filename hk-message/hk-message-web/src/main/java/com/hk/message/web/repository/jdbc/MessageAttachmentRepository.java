package com.hk.message.web.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.message.web.domain.MessageAttachment;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface MessageAttachmentRepository extends StringIdJdbcRepository<MessageAttachment> {

    @Query(value = "SELECT id,message_id,attachment_id,attachment_path,attachment_name " +
            "FROM t_message_attachment WHERE message_id = :messageId")
    Collection<MessageAttachment> findByMessageId(@Param(value = "messageId") String messageId);
}
