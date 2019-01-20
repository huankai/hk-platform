package com.hk.message.web.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.message.web.domain.MessageUser;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MessageUserRepository extends StringIdJdbcRepository<MessageUser> {

    @Query(value = "SELECT id,message_id,user_id,read_state,read_date FROM t_message_user WHERE message_id = :messageId AND user_id = :userId")
    Optional<MessageUser> findByMessageIdAndUserId(@Param(value = "messageId") String messageId, @Param(value = "userId") String userId);
}
