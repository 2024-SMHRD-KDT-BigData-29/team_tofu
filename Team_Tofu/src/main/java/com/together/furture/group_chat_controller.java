package com.together.furture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.furture.entity.group_chat;
import com.together.furture.mapper.group_chat_mapper;

@Controller
public class group_chat_controller {

    @Autowired
    private group_chat_mapper gc_mapper;

    // 그룹채팅 페이지 이동 (GET)
    @GetMapping("/group_chat")
    public String group_chat_page() {
        return "group_chat"; // /WEB-INF/views/group_chat.jsp로 이동
    }

    // 채팅 메시지 저장 (POST) - AJAX 전용 (현재는 WebSocket이 처리함)
    @PostMapping("/chat/send")
    @ResponseBody
    public void sendMessage(@RequestBody group_chat chat) {
        gc_mapper.insert(chat);
    }

    // 채팅 메시지 조회 (GET)
    @GetMapping("/chat/messages/{croom_idx}")
    @ResponseBody
    public List<group_chat> getMessages(@PathVariable("croom_idx") int croom_idx) {
        return gc_mapper.select_by_croom_idx(croom_idx);
    }
} 
