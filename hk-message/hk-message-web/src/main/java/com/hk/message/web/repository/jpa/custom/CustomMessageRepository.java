package com.hk.message.web.repository.jpa.custom;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.message.web.dto.MessageUserDTO;

public interface CustomMessageRepository {

    /**
     * 查询用户消息
     *
     * @param query query
     * @return {@link MessageUserDTO}
     */
    QueryPage<MessageUserDTO> queryUserMessage(QueryModel<MessageUserDTO> query);
}
