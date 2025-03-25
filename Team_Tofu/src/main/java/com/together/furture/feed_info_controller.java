package com.together.furture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.together.furture.entity.feed_info;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;

@Controller
public class feed_info_controller {

	@Autowired
	feed_info_mapper mapper;
	@Autowired
	user_info_mapper usermapper;

	// find 페이지 이동
	@GetMapping("/find")
	public String findPage() {
		System.out.println("find 페이지 이동");

		return "find";
	}

	// main 페이지 이동
	@GetMapping("/main")
	public String mainPage() {
		System.out.println("피드로 이동");
		return "main";
	}
	
	@GetMapping("/mypage")
	public String myPage() {
		System.out.println("마이페이지 이동");
		return "mypage";
	}
	
	@GetMapping("/mypost")
	public String myPost() {
		System.out.println("mypost 이동");
		return "mypost";
	}
	

//	@RequestMapping("/insertfeed.do")
//	public String insertfeed(feed_info user, HttpServletRequest request) {
//
//		user_info use = (user_info) (request.getSession().getAttribute("login_user"));
//		user.setUser_id(use.getUser_id());
//
//		request.getSession().setAttribute("login_user", user);
//
//		return " ";
//	}
}
