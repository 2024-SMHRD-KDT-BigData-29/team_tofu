package com.together.furture.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.together.furture.entity.personal_msg;
import com.together.furture.mapper.personal_msg_mapper;

@Component
public class PersonalChatHandler extends TextWebSocketHandler {

	@Autowired
	private personal_msg_mapper mapper;

	private static final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userId = getUserId(session);
		if (userId != null) {
			userSessions.put(userId, session);
		}
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// JSON 문자열을 personal_msg 객체로 변환
		personal_msg msg = objectMapper.readValue(message.getPayload(), personal_msg.class);

		// DB 저장
		mapper.insert_msg(msg);

		// JSON 메시지로 변환
		String jsonMessage = objectMapper.writeValueAsString(msg);

		// 🔁 1. 발신자 세션 확인 후 → 자기 화면에 메시지 출력
		WebSocketSession senderSession = userSessions.get(msg.getSender_id());
		if (senderSession != null && senderSession.isOpen()) {
			senderSession.sendMessage(new TextMessage(jsonMessage));
		}

		// 📩 2. 수신자 세션 확인 후 → 상대방에게 메시지 전송
		WebSocketSession receiverSession = userSessions.get(msg.getReceiver_id());
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.sendMessage(new TextMessage(jsonMessage));
		}
	}

	private String getUserId(WebSocketSession session) {
		return (String) session.getAttributes().get("user_id");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status)
			throws Exception {
		String userId = getUserId(session);
		if (userId != null) {
			userSessions.remove(userId);
		}
	}
}
