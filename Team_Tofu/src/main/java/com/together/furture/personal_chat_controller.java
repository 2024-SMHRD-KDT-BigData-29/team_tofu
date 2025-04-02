package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.together.furture.entity.personal_msg;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.group_chat_mapper;
import com.together.furture.mapper.personal_msg_mapper;

@Controller
public class personal_chat_controller {

	@Autowired
	personal_msg_mapper mapper;

	@Autowired
	group_chat_mapper groupMapper;

	@GetMapping("/personal_chat")
	public String openPersonalChat(@RequestParam("receiver_id") String receiverId,
			@RequestParam("receiver_nick") String receiverNick, Model model, HttpServletRequest request) {

		user_info loginUser = (user_info) request.getSession().getAttribute("login_user");
		if (loginUser == null) {
			// 로그인 안 되어 있으면 메인으로 보내자 (또는 로그인 페이지)
			return "redirect:/main.do";
		}
		// ✅ 1. 읽음 처리
		int updated = mapper.markMessagesAsRead(loginUser.getUser_id(), receiverId);
		System.out.println("읽음 처리된 메시지 수: " + updated);

		// ✅ 2. 이전 대화 불러오기
		List<personal_msg> chatHistory = mapper.getChatHistory(loginUser.getUser_id(), receiverId);

		// ✅ 3. JSP로 넘겨줌
		model.addAttribute("receiver_id", receiverId);
		model.addAttribute("receiver_nick", receiverNick);
		model.addAttribute("login_user", loginUser);
		model.addAttribute("chatHistory", chatHistory);

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
