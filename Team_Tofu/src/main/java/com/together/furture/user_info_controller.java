package com.together.furture;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.user_info_mapper;
import com.together.furture.util.FileUploadUtil;

@Controller
public class user_info_controller {

	@Autowired
	user_info_mapper mapper;
	
	@Autowired
    private FileUploadUtil fileUploadUtil;

	// 회원가입 페이지 이동(GET) -> join.jsp로 이동
	@GetMapping("/join")
	public String joinPage() {
		System.out.println("회원가입 페이지 이동");
		return "join"; // "WEB-INF/views/join.jsp"로 이동
	}
	
	// 회원가입 처리
	@RequestMapping("/user_join.do")
	public String user_join(user_info user, Model model, HttpServletRequest request) {
		
		MultipartRequest multi = null;

        try {
            multi = fileUploadUtil.uploadFile(request);

            String profile_name = multi.getFilesystemName("user_profile");
            String user_id = multi.getParameter("user_id");
            String user_pw = multi.getParameter("user_pw");
            String user_gender = multi.getParameter("user_gender");
            String user_intro = multi.getParameter("user_intro");
            String user_nick = multi.getParameter("user_nick");
            String user_email = multi.getParameter("user_email");

            user = new user_info(user_id, user_pw, user_nick, user_gender, user_email, user_intro, profile_name, null, null);
            System.out.println("종현 check : " + user.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        mapper.user_join(user);
        model.addAttribute("user_id", user.getUser_id());
        System.out.println("회원가입 성공");
        return "login";
    }

	// 로그인 처리
	@RequestMapping("/user_login.do")
	public String user_login(user_info user, HttpServletRequest request) {

		user_info login_user = mapper.user_login(user);

		if (login_user == null) {
			System.out.println("로그인 실패");
			return "redirect:/"; // 로그인 페이지로 리다이렉트
		}
		request.getSession().setAttribute("user_id", login_user.getUser_id());
		request.getSession().setAttribute("user_nick", login_user.getUser_nick());
		request.getSession().setAttribute("login_user", login_user);
		System.out.println("로그인 성공, user_id: " + login_user.getUser_id());
		return "redirect:/main";
	}
	
	// 로그아웃 처리
	@RequestMapping("logout")
	public String user_out(HttpServletRequest request) {
		request.getSession().removeAttribute("login_user");
		
		return "redirect:/";
	}

}
