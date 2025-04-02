package com.together.furture.config;

import com.together.furture.websocket.GroupChatWebSocketHandler;
import com.together.furture.websocket.PersonalChatHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private PersonalChatHandler personalChatHandler;

    @Autowired
    private GroupChatWebSocketHandler groupChatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 개인 메시지 웹소켓
        registry.addHandler(personalChatHandler, "/personal")
                .addInterceptors(new HttpHandshakeInterceptor())
                .setAllowedOrigins("*");

        // 그룹 채팅 웹소켓
        registry.addHandler(groupChatHandler, "/group")
                .setAllowedOrigins("*");
    }
}
