package com.hk.message.web.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.message.web.domain.MessageUser;

import java.util.Optional;
import java.util.Set;

public interface MessageUserService extends JdbcBaseService<MessageUser, String> {

    Optional<MessageUser> findByMessageIdAndUserId(String messageId, String userId);

    /**
     * 批量保存消息用户
     *
     * @param messageId messageId
     * @param userIds   用户id
     */
    void batchInsertMessageUser(String messageId, Set<String> userIds);

    /**
     * 用户读取消息
     *
     * @param messageId messageId
     * @param userId userId
     * @return {@link MessageUser}
     */
    MessageUser read(String messageId, String userId);

}
