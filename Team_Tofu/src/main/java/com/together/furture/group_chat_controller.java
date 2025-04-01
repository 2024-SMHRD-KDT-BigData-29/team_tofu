package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.furture.entity.group_chat;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.group_chat_mapper;

@Controller
public class group_chat_controller {

	@Autowired
	private group_chat_mapper gc_mapper;

	// 그룹채팅 페이지 이동 (GET)
	@GetMapping("/group_chat")
	public String group_chat_page(@RequestParam("croom_idx") int croom_idx, HttpServletRequest request, Model model) {
		// 로그인 유저 정보
		user_info loginUser = (user_info) request.getSession().getAttribute("login_user");

		// 채팅 메시지 가져오기
		List<group_chat> messages = gc_mapper.select_by_croom_idx(croom_idx);

		model.addAttribute("chatMessages", messages);
		model.addAttribute("croom_idx", croom_idx);
		model.addAttribute("login_user", loginUser);

		return "group_chat";
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
