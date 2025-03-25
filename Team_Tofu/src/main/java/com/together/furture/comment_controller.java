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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.together.furture.entity.comment_info;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.comment_mapper;
import com.together.furture.mapper.user_info_mapper;

import lombok.RequiredArgsConstructor;

@Controller
public class comment_controller {
	
	@Autowired
	comment_mapper mapper;
	
	
	// 댓글 저장
	@RequestMapping("/comment_send.do")
	public String comment_send(HttpServletRequest request, comment_info cmt) {
	    	
		user_info user = (user_info) request.getSession().getAttribute("login_user");
		
		if (user != null) {
	        // user_id를 세션에서 가져와 설정
			String cmtContent = request.getParameter("cmt_content");
	        cmt.setCmt_content(cmtContent);
	        cmt.setUser_id(user.getUser_id());

	        System.out.println("댓글 요청");
	        System.out.println("user_id: " + user.getUser_id()); // 로그 확인
	        System.out.println("댓글 내용: " + cmt.getCmt_content());
	        System.out.println("feed_idx: " + cmt.getFeed_idx());
	        System.out.println("created_at: " + cmt.getCreated_at());
	        System.out.println("cmt_idx: " + cmt.getCmt_idx());
		        
	        mapper.comment_send(cmt);
	    } else {
	        System.out.println("❌ 로그인한 사용자가 없음!");
	    }

	    return "redirect:/comment_show.do";
		
	}
	
    // 댓글 조회
    @RequestMapping("/comment_show.do")
    public String comment_show(HttpServletRequest request, Model model) {

    	user_info user = (user_info) request.getSession().getAttribute("login_user");

    	if(user != null) {
    		List<comment_info> cmt_list = mapper.comment_show(user.getUser_id());
    		model.addAttribute("cmt_List", cmt_list);
    	}

        return "main"; // 댓글이 포함된 게시글 상세 페이지
    }

}
