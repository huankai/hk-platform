package com.hk.message.feign;


import com.hk.commons.JsonResult;
import com.hk.core.page.SimpleQueryPage;
import com.hk.core.query.QueryModel;
import com.hk.message.domain.MessageAttachmentDTO;
import com.hk.message.domain.MessageUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = MessageService.SERVICE_NAME, path = MessageService.CONTEXT_PATH, decode404 = true, fallback = MessageFeignFallback.class)
@RequestMapping("/messages")
public interface MessageFeign {

    /**
     * 当前用户读取消息
     *
     * @param messageId messageId
     * @return {@link MessageAttachmentDTO}
     */
    @GetMapping(value = "read/{messageId}")
    JsonResult<MessageAttachmentDTO> read(@PathVariable String messageId);

    /**
     * 用户消息
     *
     * @param query query
     * @return {@link MessageUserDTO}
     */
    @PostMapping("list")
    JsonResult<SimpleQueryPage<MessageUserDTO>> list(@RequestBody QueryModel<MessageUserDTO> query);

}
