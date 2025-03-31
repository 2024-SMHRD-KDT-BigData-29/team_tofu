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

			user = new user_info(user_id, user_pw, user_nick, user_gender, user_email, user_intro, profile_name, null,
					null);
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

	// 회원정보 수정 창 진입
	@RequestMapping("/user_edit")
	public String user_edit() {
		System.out.println("회원정보 수정 페이지로 이동");

		return "edit_profile";
	}

	// 회원정보 수정 처리
	@PostMapping("/user_edit.do")
	public String user_edit(user_info user, Model model, HttpServletRequest request) {
		MultipartRequest multi = null;

		try {
			// 파일 업로드 처리
			multi = fileUploadUtil.uploadFile(request);
			if (multi == null) {
				model.addAttribute("error", "파일 업로드 실패");
				return "edit_profile";
			}

			// 세션에서 현재 로그인된 사용자 정보 가져오기
			HttpSession session = request.getSession();
			user_info login_user = (user_info) session.getAttribute("login_user");
			if (login_user == null) {
				System.out.println("세션에 로그인 정보 없음");
				model.addAttribute("error", "로그인이 필요합니다.");
				return "redirect:/"; // 로그인 페이지로 리다이렉트
			}

			// 입력값 가져오기 (null 체크 포함)
			String profile_name = multi.getFilesystemName("user_profile");
			String user_id = multi.getParameter("user_id") != null ? multi.getParameter("user_id")
					: login_user.getUser_id();
			String user_pw = multi.getParameter("user_pw") != null && !multi.getParameter("user_pw").isEmpty()
					? multi.getParameter("user_pw")
					: login_user.getUser_pw();
			String user_gender = multi.getParameter("user_gender") != null
					&& !multi.getParameter("user_gender").isEmpty() ? multi.getParameter("user_gender")
							: login_user.getUser_gender();
			String user_intro = multi.getParameter("user_intro") != null ? multi.getParameter("user_intro")
					: login_user.getUser_intro();
			String user_nick = multi.getParameter("user_nick") != null && !multi.getParameter("user_nick").isEmpty()
					? multi.getParameter("user_nick")
					: login_user.getUser_nick();
			String user_email = multi.getParameter("user_email") != null && !multi.getParameter("user_email").isEmpty()
					? multi.getParameter("user_email")
					: login_user.getUser_email();

			// 새 객체 생성 (기존 값 유지)
			user = new user_info(user_id, // user_id는 항상 유지
					user_pw, // 비밀번호는 NOT NULL이므로 기존 값 유지
					user_nick, // 닉네임은 NOT NULL이므로 기존 값 유지
					user_gender, // 성별은 NOT NULL이므로 기존 값 유지
					user_email, // 이메일은 NOT NULL이므로 기존 값 유지
					user_intro, // 소개글은 null 허용 가능
					profile_name != null ? profile_name : login_user.getUser_profile(), // 프로필 사진은 새 값이 없으면 기존 값
					login_user.getUser_role(), // 회원구분 유지
					login_user.getJoined_at() // 가입일자 유지
			);
			System.out.println("대존잘 김원영 check : " + user.toString());

			// DB 업데이트
			mapper.user_update(user);

			// 세션 업데이트 (필요 시)
			session.setAttribute("login_user", user);

			// 모델에 데이터 추가
			model.addAttribute("user_id", user.getUser_id());
			model.addAttribute("user_nick", user.getUser_nick());
			model.addAttribute("user_email", user.getUser_email());
			model.addAttribute("user_gender", user.getUser_gender());
			model.addAttribute("user_intro", user.getUser_intro());
			model.addAttribute("user_profile", user.getUser_profile());
			model.addAttribute("user_division", user.getUser_role());
			model.addAttribute("joined_at", user.getJoined_at());
			System.out.println("회원정보 수정 성공");

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("error", "파일 업로드 중 오류 발생");
			return "edit_profile";
		}

		return "redirect:/main";
	}

	// 회원 탈퇴 처리
	@RequestMapping("/user_delete")
	public String delete() {
		System.out.println("회원탈퇴 페이지 이동");

		return "user_delete";
	}

	@PostMapping("/user_delete.do")
	public String user_delete(user_info user, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		user = (user_info) session.getAttribute("login_user");
		
		if (user == null) {
	        model.addAttribute("error", "로그인이 필요합니다.");
	        return "redirect:/"; // 또는 로그인 페이지로 리다이렉트
	    }
		
		// 사용자 삭제 처리
		int result = mapper.user_delete(user.getUser_pw()); // user_info_mapper에 deleteUser 메서드 가정
			if (result == 1) {
				System.out.println("사용자 삭제 성공: user_id=" + user.getUser_id());
				session.invalidate(); // 세션에 저장된 유저정보 삭제
				return "redirect:/";
			} else {
				System.out.println("사용자 삭제 실패: user_id=" + user.getUser_id());
				return "user_delete"; // 삭제 페이지로 다시 이동
			}
	}
}
