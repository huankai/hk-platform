package com.hk.message.feign;

import com.hk.commons.JsonResult;
import com.hk.core.page.SimpleQueryPage;
import com.hk.core.query.QueryModel;
import com.hk.message.domain.MessageAttachmentDTO;
import com.hk.message.domain.MessageUserDTO;
import org.springframework.stereotype.Component;

@Component
public class MessageFeignFallback implements MessageFeign {

    @Override
    public JsonResult<MessageAttachmentDTO> read(String messageId) {
        return new JsonResult<>(JsonResult.Status.FAILURE, "系统繁忙，请稍后再试");
    }

    @Override
    public JsonResult<SimpleQueryPage<MessageUserDTO>> list(QueryModel<MessageUserDTO> query) {
        return new JsonResult<>(JsonResult.Status.FAILURE, "系统繁忙，请稍后再试");
    }
}
