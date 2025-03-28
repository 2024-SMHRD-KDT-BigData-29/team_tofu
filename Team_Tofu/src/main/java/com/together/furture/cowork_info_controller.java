package com.together.furture;

import java.io.IOException;
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
import com.together.furture.util.coworkUploadUtil;

@Controller
public class cowork_info_controller {
	@Autowired
	cowork_info_mapper mapper;
	@Autowired
	user_info_mapper user;
	@Autowired
	feed_info_mapper feedmapper;
	@Autowired
	private coworkUploadUtil coworkUploadUtil;

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
	public String insert_cowork(insert_cowork cowork, HttpServletRequest request) throws IOException {

		MultipartRequest multi = coworkUploadUtil.coworkupload(request);
		user_info user = (user_info) request.getSession().getAttribute("login_user");

		if (user != null) {
			String user_id = (String) request.getSession().getAttribute("user_id");
			String user_nick = (String) request.getSession().getAttribute("user_nick");
			String hash_tag = (String) request.getParameter("hash_tag");
			Integer cw_limit = Integer.parseInt(multi.getParameter("cw_limit"));
			String cw_title = multi.getParameter("cw_title");
			String cw_intro = multi.getParameter("cw_intro");
			String cw_content = multi.getParameter("cw_content");
			String cw_img = multi.getFilesystemName("cw_img");

			cowork = new insert_cowork(cw_title, cw_intro, cw_content, cw_img, cw_limit, hash_tag, user_id, user_nick);

			System.out.println("원영 test2 : " + cowork.toString());

			int cnt = mapper.insertcowork(cowork);

			if (cnt == 1) {
				System.out.println("[게시물 업로드 성공]");
			} else {
				System.out.println("[게시물 업로드 실패]");
			}
		} else {
			System.out.println("로그인하지않은 사용자");
		}

		return "redirect:/find";

	}

}
