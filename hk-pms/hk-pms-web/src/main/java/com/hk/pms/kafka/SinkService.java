package com.hk.pms.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author: sjq-278
 * @date: 2018-11-05 10:16
 */
//@Component
public class SinkService {

    private Logger logger = LoggerFactory.getLogger(SinkService.class);

    /**
     * 消息接收
     */
    @StreamListener(Sink.INPUT)
    public void input(String message) {
        logger.debug(message);
    }
}
