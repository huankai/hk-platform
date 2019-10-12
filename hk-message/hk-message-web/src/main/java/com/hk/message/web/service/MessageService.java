package com.hk.message.web.service;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.message.web.domain.Message;
import com.hk.message.web.dto.MessageAttachmentDTO;
import com.hk.message.web.dto.MessageAttachmentUserDTO;
import com.hk.message.web.dto.MessageUserDTO;

public interface MessageService extends JdbcBaseService<Message, String> {

    /**
     * 用户读取消息
     *
     * @param messageId messageId
     * @return {@link MessageAttachmentDTO}
     */
    MessageAttachmentDTO read(String messageId);

    /**
     * @param query 查询用户消息列表
     * @return {@link MessageUserDTO}
     */
    QueryPage<MessageUserDTO> queryUserMessage(QueryModel<MessageUserDTO> query);

    /**
     * 保存并发送消息
     * @param message {@link MessageAttachmentUserDTO}
     */
    void saveAndSendMessage(MessageAttachmentUserDTO message);

    /**
     * 我发送的
     * @param query query
     * @return {@link Message}
     */
    QueryPage<Message> mySendForPage(QueryModel<Message> query);
}
