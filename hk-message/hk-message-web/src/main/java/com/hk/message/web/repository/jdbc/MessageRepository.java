package com.hk.message.web.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.message.web.domain.Message;
import com.hk.message.web.repository.jdbc.custom.CustomMessageRepository;

public interface MessageRepository extends StringIdJdbcRepository<Message>, CustomMessageRepository {
}
