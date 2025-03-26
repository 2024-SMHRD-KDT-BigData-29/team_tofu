package com.together.furture;

import java.io.IOException;
import java.io.WriteAbortedException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.Session;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.together.furture.entity.feed_info;
import com.together.furture.entity.insert_feed;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;

@Controller
public class feed_info_controller {

	@Autowired
	feed_info_mapper mapper;
	@Autowired
	user_info_mapper usermapper;

	// main 페이지 이동
	@GetMapping("/main")
	public String mainPage(Model model) {
		System.out.println("피드로 이동");
		List<insert_feed> feedList = mapper.getFeedList();
		model.addAttribute("feedList", feedList);
		return "main";
	}
	

	

	// 글쓰기 이동
	@RequestMapping("/write")
	public String Write() {
		System.out.println("글쓰기 이동");
		return "write";
	}
	
	
	@GetMapping("/mypost")
	public String myPost() {
		System.out.println("mypost 이동");
		return "mypost";
	}
	

	@PostMapping("/write")
	public String insert_feed(insert_feed feed, HttpServletRequest request) {
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

//		try {
//			multi = new MultipartRequest(request, save_path, max_size, encoding);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		String feed_title = request.getParameter("feed_title");
		String feed_content = request.getParameter("feed_content");
		String feed_file = request.getParameter("feed_file");
		String hash_tag = request.getParameter("hash_tag");
		String user_id = (String) request.getSession().getAttribute("user_id");

		feed = new insert_feed(feed_title, feed_content, feed_file, hash_tag, user_id);

		System.out.println("원영 test2 : " + feed.toString());

		int cnt = mapper.insertfeed(feed);

		if (cnt == 1) {
			System.out.println("[게시물 업로드 성공]");
		} else {
			System.out.println("[게시물 업로드 실패]");
		}

		return "redirect:/main";
	}

}
