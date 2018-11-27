package com.hk.pms.kafka;

import com.hk.pms.service.SysOrgDeptService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: huangkai
 * @date: 2018-11-03 15:21
 */
@Component
public class TestListener {

    private Logger logger = LoggerFactory.getLogger(TestListener.class);

//    /**
//     * 消费消息
//     *
//     * @param message
//     */
//    @StreamListener(Sink.INPUT)
//    public void simpleMessageReceive(Message<String> message) {
//        logger.info(message.getPayload());
//    }


//    /**
//     * 接收对象消息
//     *
//     * @param message
//     */
//    @StreamListener(Sink.INPUT)
//    public void objectMessageReceive(@Payload MessageVo message) {
//        logger.info(JsonUtils.serialize(message, true));
//    }

//    /**
//     * 集合消息
//     *
//     * @param messages
//     */
//    @StreamListener(Sink.INPUT)
//    public void collectionMessageReceive(@Payload List<MessageVo> messages) {
//        logger.info(JsonUtils.serialize(messages, true));
//    }


    @Autowired
    private SysOrgDeptService orgDeptService;

//    @StreamListener(Sink.INPUT)
    public void transactionMessage(String message) {
        logger.info("--------> {}", message);
        /*if (StringUtils.isNotEmpty(message)) {
            throw new ServiceException("exception...");
        }*/
//        SysOrgDept orgDept = new SysOrgDept();
//        orgDept.setDeptName(message);
//        orgDept.setOrgId("");
//        orgDept.setParentId("0");
//        orgDeptService.insert(orgDept);
    }

    @Data
    private static class MessageVo {

        private String id;

        private String name;

        private String title;

        private LocalDateTime date;
    }

}
