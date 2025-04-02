package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.furture.entity.ChatPreview;
import com.together.furture.entity.GroupChatPreview;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.group_chat_mapper;
import com.together.furture.mapper.personal_msg_mapper;

@Controller
public class MessageController {

	@Autowired
	private personal_msg_mapper mapper;

	@Autowired
	private group_chat_mapper gc_mapper;

	@ResponseBody
	@GetMapping("/unread_count")
	public int getUnreadMessageCount(HttpSession session) {
		String userId = (String) session.getAttribute("user_id");
		if (userId == null)
			return 0;

		return mapper.countUnreadMessages(userId);
	}

	@ResponseBody
	@GetMapping("/group_unread_count")
	public int getUnreadGroupCount(HttpSession session) {
		user_info loginUser = (user_info) session.getAttribute("login_user");
		if (loginUser == null)
			return 0;
		return gc_mapper.countUnreadGroupMessages(loginUser.getUser_id());
	}

	@GetMapping("/my_msg_list")
	public String myMsgList(HttpServletRequest request, HttpSession session, Model model) {
		user_info loginUser = (user_info) session.getAttribute("login_user");
		if (loginUser == null) {
			// 로그인 안된 상태라면 로그인 페이지로 이동 또는 알림 처리
			return "redirect:/login"; // 또는 "redirect:/main"
		}
		// 개인 채팅 목록
		List<ChatPreview> personalList = mapper.getChatPreviewList(loginUser.getUser_id());
		// 그룹 채팅 목록
		List<GroupChatPreview> groupList = gc_mapper.getGroupChatPreviewList(loginUser.getUser_id());

		model.addAttribute("personalList", personalList);
		model.addAttribute("groupList", groupList);
		
		System.out.println("그룹채팅목록 " + groupList);
		
		return "my_msg_list";
	}

}
