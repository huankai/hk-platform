package com.hk.sso.server.controller;

import com.hk.core.web.JsonResult;
import com.hk.message.api.publish.MessagePublish;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * webSocket Test
 *
 * @author: sjq-278
 * @date: 2018-09-21 14:26
 */
@RestController
public class WebSocketMessageController extends BaseController {

    @Autowired
    @Qualifier("webSocketMessagePublish")
    private MessagePublish messagePublish;

    @RequestMapping("/chat")
    public JsonResult<Void> handlerChat(String message) {
        //为什么是发送给 account()
        messagePublish.send(getPrincipal().getAccount(), "/queue/notifications", message);
        return JsonResult.success();
    }

    @MessageMapping("/chat2")
    public void handlerChat2(Principal principal, String message) {
//        getPrincipal().getAccount(); 使用 @MessageMapping 注解的方法不能这样获取登陆的用户信息，原因待查
        messagePublish.send(principal.getName(), "/queue/notifications", message);
    }
}
