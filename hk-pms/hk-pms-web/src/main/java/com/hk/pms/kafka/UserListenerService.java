package com.hk.pms.kafka;

import org.springframework.messaging.Message;

/**
 * @author: sjq-278
 * @date: 2018-11-03 15:21
 */
//@Service
public class UserListenerService {

//    @StreamListener(UserChannel.CREATE_OR_UPDATE_USER_INPUT)
    public void createOrUpdateUser(Message<String> message) {
        System.out.println(message.getPayload());
    }

//    @StreamListener(UserChannel.DELETE_USER_INPUT)
    public void deleteUser(Message<String> message) {
        System.out.println(message.getPayload());
    }
}
