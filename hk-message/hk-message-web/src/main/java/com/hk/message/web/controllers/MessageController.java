package com.hk.message.web.controllers;


import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.message.web.domain.Message;
import com.hk.message.web.dto.MessageAttachmentDTO;
import com.hk.message.web.dto.MessageAttachmentUserDTO;
import com.hk.message.web.dto.MessageUserDTO;
import com.hk.message.web.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息管理
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 我发送的消息查询
     *
     * @param query query
     * @return {@link MessageUserDTO}
     */
    @PostMapping("list")
    public JsonResult<QueryPage<Message>> mySendList(@RequestBody QueryModel<Message> query) {
        return JsonResult.success(messageService.mySendForPage(query));
    }

    /**
     * 用户消息分页查询
     *
     * @param query query
     * @return {@link MessageUserDTO}
     */
    @PostMapping("list")
    public JsonResult<QueryPage<MessageUserDTO>> list(@RequestBody QueryModel<MessageUserDTO> query) {
        return JsonResult.success(messageService.queryUserMessage(query));
    }

    /**
     * 保存消息
     *
     * @param message message
     * @return {@link JsonResult}
     */
    @PostMapping
    public JsonResult<Void> save(MessageAttachmentUserDTO message) {
        messageService.saveAndSendMessage(message);
        return JsonResult.success();
    }

    /**
     * 查看消息
     *
     * @param id id
     * @return {@link Message}
     */
    @GetMapping(path = "{id}", name = "message-get")
    public JsonResult<Message> get(@PathVariable String id) {
        return JsonResult.success(messageService.getById(id));
    }

    /**
     * 读取消息
     *
     * @param messageId messageId
     * @return {@link MessageAttachmentDTO}
     */
    @GetMapping(value = "read/{messageId}", name = "read-message")
    public JsonResult<MessageAttachmentDTO> read(@PathVariable String messageId) {
        return JsonResult.success(messageService.read(messageId));
    }

}
