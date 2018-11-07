package com.hk.pms.kafka;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;

/**
 * @author: sjq-278
 * @date: 2018-11-05 16:51
 */
@Component
public class TestStreamService {

    @StreamListener(Source.OUTPUT)
    public void receive(String message) {
        System.out.println("message:" + message);
    }
}
