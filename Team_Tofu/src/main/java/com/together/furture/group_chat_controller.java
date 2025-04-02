package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.together.furture.entity.group_chat;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.group_chat_mapper;
import com.together.furture.mapper.croom_info_mapper;

@Controller
public class group_chat_controller {

	@Autowired
	private group_chat_mapper gc_mapper;

	@Autowired
	private croom_info_mapper croom_mapper;

	// ✅ 그룹채팅 페이지 이동
	@GetMapping("/group_chat")
	public String group_chat_page(@RequestParam("croom_idx") int croom_idx, HttpServletRequest request, Model model) {
		user_info loginUser = (user_info) request.getSession().getAttribute("login_user");

		// 참여자 리스트
		List<user_info> participants = croom_mapper.getParticipantsByCwIdx(croom_idx);
		model.addAttribute("participants", participants);

		// 채팅 메시지
		List<group_chat> messages = gc_mapper.select_by_croom_idx(croom_idx);
		model.addAttribute("chatMessages", messages);

		// 방 정보
		model.addAttribute("croom_info", croom_mapper.getCroomByCwIdx(croom_idx));
		model.addAttribute("login_user", loginUser);

		return "group_chat";
	}

	// ✅ WebSocket 연결 전용 저장용 (실제로는 WebSocket에서도 insert 호출 가능)
	@PostMapping("/chat/send")
	@ResponseBody
	public void sendMessage(@RequestBody group_chat chat) {
		gc_mapper.insert(chat);
	}

	// ✅ 메시지 불러오기 (AJAX용)
	@GetMapping("/chat/messages")
	@ResponseBody
	public List<group_chat> getMessages(@RequestParam("croom_idx") int croom_idx) {
		System.out.println("✅ 메시지 가져오기 요청됨 - croom_idx: " + croom_idx);
		return gc_mapper.select_by_croom_idx(croom_idx);
	}
}
