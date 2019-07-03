package com.hk.message.web.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.message.web.domain.Message;
import com.hk.message.web.repository.jpa.custom.CustomMessageRepository;

public interface MessageRepository extends LongIdJpaRepository<Message>, CustomMessageRepository {
}
