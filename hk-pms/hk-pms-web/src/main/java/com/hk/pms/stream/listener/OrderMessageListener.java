package com.hk.pms.stream.listener;

import com.hk.commons.util.JsonUtils;
import com.hk.stream.order.OrderInput;
import com.hk.stream.order.OrderPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author huangkai
 * @date 2019-04-14 20:41
 */
@Slf4j
@Component
public class OrderMessageListener {

    @StreamListener(value = OrderInput.GENERATE_ORDER)
    public void receive(@Payload OrderPayload message) {
        log.info("receive Message:{}" + JsonUtils.serialize(message));
    }

}
