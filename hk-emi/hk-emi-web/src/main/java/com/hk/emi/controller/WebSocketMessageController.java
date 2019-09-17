package com.hk.emi.controller;

import com.hk.platform.commons.web.BaseController;
import org.springframework.stereotype.Controller;

/**
 * webSocket Test
 *
 * @author kevin
 * @date 2018-09-21 14:26
 */
//@Controller
public class WebSocketMessageController extends BaseController {

//    @Autowired
//    @Qualifier("websocketMessager")
//    private WebsocketMessager websocketMessager;
//
//    @Autowired
//    private SimpUserRegistry userRegistry;
//
//    /**
//     * 这里的用户是 Spring security 认证的 UserPrincipal
//     *
//     * @param principal principal
//     * @param message   message
//     * @param toUser    toUser
//     * @return JsonResult
//     */
//    @RequestMapping("/chat2")
//    public JsonResult<Void> handlerChat(Principal principal, String message, Long toUser) {
//        websocketMessager.publish(ChatMessage
//                .builder().
//                        formUser(getPrincipal().getUserId())
//                .toUser(toUser)
//                .content(message).build()
//        ).to(SimpleUserMessageSubject.builder().
//                queueName("/queue/notifications")
//                .build().addUser(toUser)
//        ).send();
//        return JsonResult.success();
//    }
//
//    /**
//     * 使用 @MessageMapping 这里的 principal 是 websocket 中的 principal
//     * principal 的值默认为 {@link SecurityUserPrincipal#getUsername()}，
//     * 但这边里重写了，请查看 {@link com.hk.message.websocket.handlers.PrincipalHandshakeHandler#determineUser(ServerHttpRequest, WebSocketHandler, Map)}方法
//     *
//     * @param principal webSocket principal
//     * @param message   发送的消息
//     */
//    @MessageMapping("/chat")
//    public void handlerChat2(Principal principal, @RequestParam String message) {
//        Map<?, ?> map = JsonUtils.deserialize(message, Map.class);
//        Long toUser = (Long) map.get("toUser");
//        websocketMessager.publish(ChatMessage
//                .builder()
////                        .formUser(principal.getName())
//                .toUser(toUser)
//                .content(map.get("message")).build()
//        ).to(SimpleUserMessageSubject.builder().
//                queueName("/queue/notifications")
//                .build().addUser(toUser)
//        ).send();
//    }
//
//    /**
//     * 当客户端订阅了 {@link SubscribeMapping } 的值时，会请求一次。
//     * <p>
//     * 在请求-回应模式中，客户端订阅一个目的地，然后预期在这个目的地上 获得一个一次性的 响应
//     * <p>
//     * 使用 @MessageMapping 这里的 principal 是 websocket 中的 principal
//     * principal 的值默认为 {@link SecurityUserPrincipal#getUsername()}，
//     * 但这边里重写了，请查看 {@link com.hk.message.websocket.handlers.PrincipalHandshakeHandler#determineUser(ServerHttpRequest, WebSocketHandler, Map)}方法
//     *
//     * @param principal webSocket principal
//     */
//    @SubscribeMapping("/user/queue/notifications")
//    public List<String> subscribeNotification(Principal principal) {
//        // 获取用户消息
//        List<String> messages = new ArrayList<>();
//        messages.add("登陆成功消息");
//        messages.add("用户消息1");
//        messages.add("用户消息2");
//        return messages;
//    }
//
//
//    /**
//     * 当客户端订阅了 {@link SubscribeMapping } 的值时，会请求一次。
//     * <p>
//     * 在请求-回应模式中，客户端订阅一个目的地，然后预期在这个目的地上 获得一个一次性的 响应
//     * <p>
//     */
//    @SubscribeMapping("/queue/onlineuser")
//    public int subscribeOnlineuser() {
//        int userCount = userRegistry.getUserCount();
//        websocketMessager.publish(OnLineUserMessage
//                .builder()
//                .onLineUser(userCount)
//                .build())
//                .to(SimpleTopicMessageSubject.builder().topic("/queue/onlineuser").build())
//                .send();
//        return userCount;
//    }
}
