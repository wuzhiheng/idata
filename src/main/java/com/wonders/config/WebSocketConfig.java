package com.wonders.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
/**
 * @Author : wuzhiheng
 * @Description : 配置websocket
 * @Date Created in 11:24 上午 2020/3/16
 */
//@Configuration
//@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //服务端发送消息给客户端的域,也就是说客户端能订阅的前缀
        config.enableSimpleBroker("/topic","/session");

        //客户端发送给服务端路径的前缀，@MessageMapping 不带这一个前缀
        config.setApplicationDestinationPrefixes("/ws");

        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        config.setUserDestinationPrefix("/user/");
    }

    /**
     * 点对点发送消息
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/zg_indicator").withSockJS();
    }

}
