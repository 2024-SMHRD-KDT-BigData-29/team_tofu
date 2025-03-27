package com.together.furture;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.servlet.MultipartRequest;
import com.together.furture.entity.comment_insert;
import com.together.furture.entity.insert_feed;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.comment_mapper;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;
import com.together.furture.util.feedUploadUtile;

@Controller
public class feed_info_controller {

	@Autowired
	feed_info_mapper mapper;
	@Autowired
	user_info_mapper usermapper;
	@Autowired
	comment_mapper commentmapper;
	@Autowired
    private feedUploadUtile feedUploadUtile;
	
	// main 페이지 이동
	@GetMapping("/main")
	public String mainPage(Model model) {
		
		System.out.println("피드로 이동");
		List<insert_feed> feedList = mapper.getFeedList();
		model.addAttribute("feedList", feedList);
		
		List<comment_insert> cmtList = commentmapper.comment_show();
		System.out.println("cmtList길이 확인>>" + cmtList.size());
		model.addAttribute("cmtList", cmtList);
		return "main";
	}
	

	

	// 글쓰기 이동
	@RequestMapping("/write")
	public String Write() {
		System.out.println("글쓰기 이동");
		return "write";
	}
	
	// 경로만 추가 -> 내가 쓴글 보기 
	@GetMapping("/mypost")
	public String myPost() {
		System.out.println("mypost 이동");
		return "mypost";
	}
	
	 // 게시글 작성
	   @PostMapping("/write")
	   public String insert_feed(insert_feed feed, HttpServletRequest request) throws IOException {
	      

	      MultipartRequest multi = feedUploadUtile.feedupload(request);


	      user_info user = (user_info) request.getSession().getAttribute("login_user");
	      String user_id = (String) request.getSession().getAttribute("user_id");
	      String user_nick = (String) request.getSession().getAttribute("user_nick");
	      String user_profile = (String) request.getSession().getAttribute("user_profile");
	      
	      String feed_title = multi.getParameter("feed_title");
	      String feed_content = multi.getParameter("feed_content");
	      String hash_tag = multi.getParameter("hash_tag");
	      String feed_file = multi.getFilesystemName("feed_file");
	      
	      // feed_title null 체크
	      if (feed_title == null || feed_title.trim().isEmpty()) {
	            throw new IllegalArgumentException("feed_title은 필수 입력값입니다.");
	        }
	      
	      feed = new insert_feed(feed_title, feed_content, feed_file, hash_tag, user_id, user_nick, 0, user_profile);
	      
	      System.out.println("원영 test2 : " + user_nick);
	      
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
