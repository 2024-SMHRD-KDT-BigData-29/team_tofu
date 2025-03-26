package com.together.furture;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.together.furture.entity.comment_info;
import com.together.furture.entity.comment_insert;
import com.together.furture.entity.feed_info;
import com.together.furture.entity.insert_feed;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.comment_mapper;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;

import lombok.RequiredArgsConstructor;

@Controller
public class comment_controller {
	
	@Autowired
	comment_mapper mapper;
	@Autowired
	user_info_mapper usermapper;
	@Autowired
	feed_info_mapper feedmapper;
	
	// 댓글 저장
	@PostMapping("/comment_send")
	public String comment_send(HttpServletRequest request, comment_insert cmt) {
		user_info user = (user_info) request.getSession().getAttribute("login_user");
		if (user != null) {
			
			String cmt_content = request.getParameter("cmt_content");
			String user_id = (String) request.getSession().getAttribute("user_id");
			String user_nick = (String) request.getSession().getAttribute("user_nick");
			Integer feed_idx = Integer.parseInt(request.getParameter("feed_idx"));

			cmt  = new comment_insert(user_nick, cmt_content, user_id, feed_idx);

			System.out.println("댓글 내용 : " + cmt_content);
			System.out.println("작성자 아이디 : " + user_id);
			System.out.println("작성자 닉네임 : " + user_nick);
			System.out.println("작성자 닉네임 : " + feed_idx);
			int cnt = mapper.comment_send(cmt);

			if (cnt == 1) {
				System.out.println("댓글 작성 성공");
			}else {
				System.out.println("댓글 작성 실패");
			}
		}else {
			System.out.println("로그인하지않은 사용자");
		}
		
	    return "redirect:/main";
		
	}
	
  

}
