package com.hk.sso.server.kafka.controller;

import com.hk.commons.util.ArrayUtils;
import com.hk.core.web.JsonResult;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 消息发送测试类
 *
 * @author: sjq-278
 * @date: 2018-11-05 10:12
 */
@RestController
@RequestMapping("/kafka/message")
public class KafkaSourceTestController {

    private final Source source;

    @Autowired
    public KafkaSourceTestController(Source source) {
        this.source = source;
    }

    /**
     * 发送消息
     *
     * @param message message
     * @return JsonResult
     */
    @RequestMapping("/simple")
    public JsonResult<Void> simpleMessage(String message) {
        source.output().send(MessageBuilder.withPayload(message).build());
        return JsonResult.success();
    }

    /**
     * 发送对象消息
     *
     * @param message message
     * @return JsonResult
     */
    @RequestMapping("/object")
    public JsonResult<Void> objectMessage(Message message) {
        message.setDate(LocalDateTime.now());
        source.output().send(MessageBuilder.withPayload(message).build());
        return JsonResult.success();
    }

    /**
     * 发送集合消息
     *
     * @param message message
     * @return JsonResult
     */
    @RequestMapping("/list")
    public JsonResult<Void> collectionMessage(Message message) {
        message.setDate(LocalDateTime.now());
        source.output().send(MessageBuilder.withPayload(ArrayUtils.asArrayList(message)).build());
        return JsonResult.success();
    }

    @Data
    public static class Message {

        private String id;

        private String name;

        private String title;

        private LocalDateTime date;
    }

}
