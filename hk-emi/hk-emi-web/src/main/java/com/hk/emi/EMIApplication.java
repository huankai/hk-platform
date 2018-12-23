package com.hk.emi;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import java.time.LocalDateTime;

/**
 * EMI Start
 *
 * @author kevin
 * @date 2018-07-13 14:06
 */
@SpringCloudApplication
//@EnableBinding(Sink.class)
public class EMIApplication {

    private final Logger logger = LoggerFactory.getLogger(EMIApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EMIApplication.class, args);
    }

//    @StreamListener(Sink.INPUT)
//    public void transactionMessage(List<MessageVo> messages) {
//        logger.info("--------> {}", messages);
////        SysOrgDept orgDept = new SysOrgDept();
////        orgDept.setDeptName(message);
////        orgDept.setOrgId("");
////        orgDept.setParentId("0");
////        orgDeptService.insert(orgDept);
//    }

    @Data
    private static class MessageVo {

        private String id;

        private String name;

        private String title;

        private LocalDateTime date;
    }
}
