package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.together.furture.entity.personal_msg;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.personal_msg_mapper;

@Controller
public class personal_chat_controller {

    @Autowired
    personal_msg_mapper mapper;

    @GetMapping("/personal_chat")
    public String openPersonalChat(@RequestParam("receiver_id") String receiverId,
                                   @RequestParam("receiver_nick") String receiverNick,
                                   Model model, HttpServletRequest request) {

        user_info loginUser = (user_info) request.getSession().getAttribute("login_user");

        // 👉 기존 채팅 내역 가져오기
        List<personal_msg> chatHistory = mapper.getChatHistory(loginUser.getUser_id(), receiverId);

        model.addAttribute("receiver_id", receiverId);
        model.addAttribute("receiver_nick", receiverNick);
        model.addAttribute("login_user", loginUser);
        model.addAttribute("chatHistory", chatHistory); // 💬 이전 메시지들

        return "personal_chat";
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody personal_msg msg) {
        msg.setIs_read("N");
        mapper.insertMessage(msg);
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<personal_msg> getMessages(@RequestParam("sender_id") String senderId,
                                          @RequestParam("receiver_id") String receiverId) {
        return mapper.getMessagesBetween(senderId, receiverId);
    }
}


