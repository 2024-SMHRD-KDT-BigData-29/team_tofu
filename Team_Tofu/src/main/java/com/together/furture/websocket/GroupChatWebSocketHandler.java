package com.together.furture.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.together.furture.entity.group_chat;
import com.together.furture.mapper.group_chat_mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class GroupChatWebSocketHandler implements WebSocketHandler {

    private final group_chat_mapper chatMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 각 방마다 접속된 사용자들을 관리
    private final Map<Integer, List<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 연결 완료 로그
        System.out.println("🟢 그룹 채팅 WebSocket 연결됨");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        group_chat chat = objectMapper.readValue(payload, group_chat.class);

        // DB에 저장
        chatMapper.insert(chat);

        // 해당 채팅방 사용자들에게 메시지 전송
        int roomId = chat.getCroom_idx();
        roomSessions.putIfAbsent(roomId, new ArrayList<>());

        // 현재 세션을 room에 등록 (중복 방지)
        if (!roomSessions.get(roomId).contains(session)) {
            roomSessions.get(roomId).add(session);
        }

        // 메시지 전파
        String jsonMsg = objectMapper.writeValueAsString(chat);
        for (WebSocketSession s : roomSessions.get(roomId)) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(jsonMsg));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.out.println("❌ 그룹 채팅 오류 발생: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 연결 종료 시 모든 방에서 세션 제거
        for (List<WebSocketSession> sessions : roomSessions.values()) {
            sessions.remove(session);
        }
        System.out.println("🔴 그룹 채팅 연결 종료");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
