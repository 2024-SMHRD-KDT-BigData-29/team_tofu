package com.together.furture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.together.furture.entity.user_info;
import com.together.furture.mapper.user_info_mapper;

@Controller
public class user_info_controller {
	
	@Autowired
	user_info_mapper mapper;
	
	// 회원가입 페이지 이동(GET) -> join.jsp로 이동
	@GetMapping("/join")
	public String joinPage() {
	    System.out.println("회원가입 페이지 이동");
	    return "join";  // "WEB-INF/views/join.jsp"로 이동
	}
	
	
	// 회원가입 처리
	@RequestMapping("user_join.do")
	public String user_join(user_info user, Model model) {
				
		mapper.user_join(user);
		model.addAttribute("user_id", user.getUser_id());
		
		return "join";
	}
	
	
	// 로그인 처리
	@RequestMapping("/user_login.do")
	public String memberLogin(user_info user, HttpServletRequest request) {
		
		user_info login_user = mapper.user_login(user);
	    
	    request.getSession().setAttribute("login_user", login_user);
				
		return "main";
	}
}
