package com.hk.message.web.repository.jpa.impl;

import com.hk.core.page.QueryPage;
import com.hk.core.page.SimpleQueryPage;
import com.hk.core.query.QueryModel;
import com.hk.message.web.dto.MessageUserDTO;
import com.hk.message.web.repository.jpa.custom.CustomMessageRepository;

public class MessageRepositoryImpl implements CustomMessageRepository {

    @Override
    public QueryPage<MessageUserDTO> queryUserMessage(QueryModel<MessageUserDTO> query) {
        return new SimpleQueryPage<>();
//        SelectArguments arguments = new SelectArguments();
//        arguments.fields("id", "title", "msg_content", "sender_name", "is_read", "read_date");
//        arguments.setFrom("t_message t1 JOIN t_message_user t2 ON t1.message_id = t2.message_id");
//        MessageUserDTO param = query.getParam();
//        arguments.getConditions().addConditions(
//                new SimpleCondition("t2.user_id", param.getUserId()),
//                new SimpleCondition("t2.is_read", param.getIsRead()),
//                new SimpleCondition("t1.title", Operator.LIKEANYWHERE, param.getTitle()));
//        return jdbcSession.queryForPage(arguments, MessageUserDTO.class);
    }
}
