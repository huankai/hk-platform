package com.hk.message.web.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.message.web.domain.MessageUser;

import java.util.Optional;

public interface MessageUserRepository extends LongIdJpaRepository<MessageUser> {

    //    @Query(value = "SELECT id,message_id,user_id, is_read,read_date FROM t_message_user WHERE message_id = :messageId AND user_id = :userId")
    Optional<MessageUser> findByMessageIdAndUserId(Long messageId, Long userId);
}
