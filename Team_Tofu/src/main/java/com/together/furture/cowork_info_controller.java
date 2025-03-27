package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.servlet.MultipartRequest;
import com.together.furture.entity.insert_cowork;
import com.together.furture.entity.insert_feed;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.cowork_info_mapper;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;

@Controller
public class cowork_info_controller {
	@Autowired
	cowork_info_mapper mapper;
	@Autowired
	user_info_mapper user;
	@Autowired
	feed_info_mapper feedmapper;

	// find 페이지 이동
	@GetMapping("/find")
	public String findPage(Model model) {
		System.out.println("find 페이지 이동");
		List<insert_cowork> coworkList = mapper.getCoworkList();
		model.addAttribute("coworkList", coworkList);
		List<insert_feed> feedList = feedmapper.getFeedList();
		model.addAttribute("feedList", feedList);
		return "find";
	}

	// 마이페이지 이동
	@GetMapping("/mypage")
	public String myPage() {
		System.out.println("마이페이지 이동");

		return "mypage";
	}

	// 글쓰기 이동
	@RequestMapping("/find_write")
	public String find_Write() {
		System.out.println("글쓰기 이동");
		return "find_write";
	}

	// 글 작성
	@PostMapping("/find_write")
	public String insert_cowork(insert_cowork cowork, HttpServletRequest request) {
		// 파일 업로드 시 필요한 객체 -> MultipartRequest 객체
		// 1. 요청객체 -> request
		// 2. 이미지 저장할 폴더의 경로(String)
		// equest.getSession().getServletContext().getRealPath("resources/upload");
		String save_path = request.getRealPath("resources/upload");
		System.out.println("이미지 저장 경로 : " + save_path);

		// 3. 이미지 용량 크기(int)
		int max_size = 1024 * 1024 * 10;

		// 4. 파일명 인코딩 방식(String)
		String encoding = "UTF-8";

		MultipartRequest multi = null;
		if (user != null) {
			user_info user = (user_info) request.getSession().getAttribute("login_user");
			String cw_title = request.getParameter("cw_title");
			String cw_intro = request.getParameter("cw_intro");
			String cw_content = request.getParameter("cw_content"); 
			String cw_img = request.getParameter("cw_img");
			Integer cw_limit = Integer.parseInt(request.getParameter("cw_limit"));
			String hash_tag = (String) request.getParameter("hash_tag");;
			String user_id = (String) request.getSession().getAttribute("user_id");
			String user_nick = (String) request.getSession().getAttribute("user_nick");
	
			cowork = new insert_cowork(cw_title, cw_intro, cw_content, cw_img ,cw_limit, hash_tag, user_id, user_nick);
	
			System.out.println("원영 test2 : " + cowork.toString());
	
			int cnt = mapper.insertcowork(cowork);
	
			if (cnt == 1) {
				System.out.println("[게시물 업로드 성공]");
			} else {
				System.out.println("[게시물 업로드 실패]");
			}
		}else {
			System.out.println("로그인하지않은 사용자");
		}

		return "redirect:/find";

	}
}
