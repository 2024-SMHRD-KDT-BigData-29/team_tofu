package com.together.furture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.together.furture.mapper.cowork_info_mapper;

@Controller
public class cowork_info_controller {
	@Autowired
	cowork_info_mapper mapper;
	
	// find 페이지 이동
	@GetMapping("/find")
	public String findPage() {
	System.out.println("find 페이지 이동");
			
	return "find";
	}
	
	// 마이페이지 이동
	@GetMapping("/mypage")
	public String myPage() {
	System.out.println("마이페이지 이동");
	
	return "mypage";
	}
	
	
	
}
