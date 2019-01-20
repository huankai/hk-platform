package com.hk.message.web.service.impl;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.CollectionUtils;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.exception.ServiceException;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.message.web.domain.MessageUser;
import com.hk.message.web.repository.jdbc.MessageUserRepository;
import com.hk.message.web.service.MessageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageUserServiceImpl extends JdbcServiceImpl<MessageUser, String> implements MessageUserService {

    @Autowired
    private MessageUserRepository messageUserRepository;

    @Override
    protected JdbcRepository<MessageUser, String> getBaseRepository() {
        return messageUserRepository;
    }

    @Override
    public Optional<MessageUser> findByMessageIdAndUserId(String messageId, String userId) {
        return messageUserRepository.findByMessageIdAndUserId(messageId, userId);
    }

    @Override
    public void batchInsertMessageUser(String messageId, Set<String> userIds) {
        if (CollectionUtils.isNotEmpty(userIds)) {
            List<MessageUser> messageUsers = new ArrayList<>(userIds.size());
            MessageUser messageUser;
            for (String userId : userIds) {
                messageUser = new MessageUser();
                messageUser.setReadState(ByteConstants.ZERO);
                messageUser.setMessageId(messageId);
                messageUser.setUserId(userId);
                messageUsers.add(messageUser);
            }
            batchInsert(messageUsers);
        }
    }

    @Override
    public MessageUser read(String messageId, String userId) {
        MessageUser messageUser = findByMessageIdAndUserId(messageId, getPrincipal().getUserId())
                .orElseThrow(() -> new ServiceException("消息不存在"));
        if (ByteConstants.ZERO.equals(messageUser.getReadState())) {
            messageUser.setReadDate(LocalDateTime.now());
            messageUser.setReadState(ByteConstants.ONE);
            updateById(messageUser);
        }
        return messageUser;
    }
}
