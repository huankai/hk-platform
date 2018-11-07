package com.hk.sso.server.kafka.controller;

import com.hk.core.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sjq-278
 * @date: 2018-11-05 10:12
 */
@RestController
@RequestMapping("/kafka/source")
public class KafkaSourceTestController {

    /**
     * 发送消息
     */
    @Autowired
    private Source source;

    /**
     * 发送消息
     *
     * @param message
     * @return
     */
    @RequestMapping
    public JsonResult<Void> index(String message) {
        source.output().send(MessageBuilder.withPayload(message).build());
        return JsonResult.success();
    }

    @StreamListener(Source.OUTPUT)
    public void message(@Payload String message) {
        System.out.println("message -> " + message);
    }


}
