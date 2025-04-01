package com.together.furture.config;

import com.together.furture.websocket.PersonalChatHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private PersonalChatHandler personalChatHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(personalChatHandler, "/personal")
				.addInterceptors(new HttpHandshakeInterceptor())
				.setAllowedOrigins("*");
	}
}
