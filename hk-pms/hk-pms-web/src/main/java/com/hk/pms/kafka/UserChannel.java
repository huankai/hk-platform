package com.hk.pms.kafka;

import org.springframework.messaging.SubscribableChannel;

/**
 * 用户用户
 *
 * @author: sjq-278
 * @date: 2018-11-03 15:17
 */
public interface UserChannel {

    /**
     * 监听消息
     */
    String CREATE_OR_UPDATE_USER_INPUT = "create_or_update_user_input";


    String DELETE_USER_INPUT = "delete_user_input";

//    /**
//     *
//     */
//    String CREATE_USER_OUTPUT = "create_user_output";
//
//    @Output(CREATE_USER_OUTPUT)
//    MessageChannel sendUserMessage();

    /**
     * 创建或更新用户的通道
     */
//    @Input(CREATE_OR_UPDATE_USER_INPUT)
    SubscribableChannel createOrUpdateUser();

    /**
     * 删除用户的通道
     */
//    @Input(DELETE_USER_INPUT)
    SubscribableChannel deleteUser();
}
