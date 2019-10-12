package com.hk.message.web.service;

import com.hk.core.service.jpa.JpaBaseService;
import com.hk.message.web.domain.MessageUser;

import java.util.Optional;
import java.util.Set;

public interface MessageUserService extends JpaBaseService<MessageUser, Long> {

    Optional<MessageUser> findByMessageIdAndUserId(Long messageId, Long userId);

    /**
     * 批量保存消息用户
     *
     * @param messageId messageId
     * @param userIds   用户id
     */
    void batchInsertMessageUser(Long messageId, Set<Long> userIds);

    /**
     * 用户读取消息
     *
     * @param messageId messageId
     * @param userId userId
     * @return {@link MessageUser}
     */
    MessageUser read(Long messageId, Long userId);

}
