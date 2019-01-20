package com.hk.message.web.service.impl;

import com.hk.commons.util.StringUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.message.web.domain.Message;
import com.hk.message.web.domain.MessageAttachment;
import com.hk.message.web.dto.MessageAttachmentDTO;
import com.hk.message.web.dto.MessageAttachmentUserDTO;
import com.hk.message.web.dto.MessageUserDTO;
import com.hk.message.web.repository.jdbc.MessageRepository;
import com.hk.message.web.service.MessageAttachmentService;
import com.hk.message.web.service.MessageService;
import com.hk.message.web.service.MessageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class MessageServiceImpl extends JdbcServiceImpl<Message, String> implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageUserService messageUserService;

    @Autowired
    private MessageAttachmentService messageAttachmentService;

    @Override
    protected JdbcRepository<Message, String> getBaseRepository() {
        return messageRepository;
    }

    @Override
    public MessageAttachmentDTO read(String messageId) {
        messageUserService.read(messageId, getPrincipal().getUserId());
        Message message = getById(messageId);
        Collection<MessageAttachment> messageAttachments = messageAttachmentService.findByMessageId(messageId);
        return new MessageAttachmentDTO(message, messageAttachments);
    }

    @Override
    public QueryPage<MessageUserDTO> queryUserMessage(QueryModel<MessageUserDTO> query) {
        MessageUserDTO param = query.getParam();
        param.setUserId(getPrincipal().getUserId());
        return messageRepository.queryUserMessage(query);
    }

    @Override
    public void saveAndSendMessage(MessageAttachmentUserDTO message) {
        UserPrincipal principal = getPrincipal();
        message.setSenderBy(principal.getUserId());
        message.setSenderName(StringUtils.isEmpty(message.getSenderName())
                ? principal.getRealName() : message.getSenderName());
        Message saved = insert(message);
        messageAttachmentService.batchInsertMessageAttachment(saved.getId(), message.getAttachments());
        Set<String> userIds = getUserIds(message);
        messageUserService.batchInsertMessageUser(saved.getId(), userIds);
    }

    @Override
    public QueryPage<Message> mySendForPage(QueryModel<Message> query) {
        query.getParam().setSenderBy(getPrincipal().getUserId());
        return queryForPage(query);
    }

    /**
     * 获取发送消息的用户id
     *
     * @param message message
     * @return 发送消息的用户id 集合
     */
    private Set<String> getUserIds(MessageAttachmentUserDTO message) {
        Set<String> userIds = null;
        switch (message.getSenderTo()) {
            case "1": //教师
                break;
            case "2":
                break;
            case "3": //发送给指定用户
                userIds = message.getUserIds();
                break;
            default:
                break;
        }
        return userIds;
    }
}
