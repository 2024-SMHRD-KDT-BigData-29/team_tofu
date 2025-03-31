package com.together.furture;

import org.springframework.web.bind.annotation.RequestMapping;

public class group_chat_controller {
	@RequestMapping("/group_chat")
	public String group_chat() {
		System.out.println("그룹채팅 시작!");
		return "group_chat";
	}
}
