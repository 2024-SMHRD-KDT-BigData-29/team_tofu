package com.together.furture;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.together.furture.entity.croom_info;
import com.together.furture.entity.insert_cowork;
import com.together.furture.entity.insert_feed;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.cowork_info_mapper;
import com.together.furture.mapper.croom_info_mapper;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;


@Controller
public class croom_info_controller {

	@Autowired
	cowork_info_mapper comapper;
	@Autowired
	user_info_mapper user;
	@Autowired
	feed_info_mapper feedmapper;
	@Autowired
	croom_info_mapper mapper;



	@PostMapping("find_detail")
	public String find_detail(@RequestParam("sel") int cw_idx, Model model, HttpSession session) {
		insert_cowork cowork = comapper.getCoworkById(cw_idx);
		model.addAttribute("insert_cowork", cowork);
		user_info login_user = (user_info) session.getAttribute("login_user");
		
		if (login_user == null) {
			return "login";
		}
		String currentUserId = login_user.getUser_id() != null ? login_user.getUser_id() : "unknown";

		croom_info croom = mapper.getCroomByCwIdx(cw_idx);

		if (croom == null) {
			croom = new croom_info();
			croom.setCw_idx(cw_idx);
			croom.setCroom_title(cowork.getCw_title());
			croom.setCroom_info(cowork.getCw_intro() != null ? cowork.getCw_intro() : "기본 소개");
			croom.setCroom_img(cowork.getCw_img() != null ? cowork.getCw_img() : "default.jpg");
			croom.setCroom_limit(1);
			croom.setUser_id(cowork.getUser_id());
			croom.setCroom_status("ACTIVE");
			mapper.createCroom(croom);
			mapper.addParticipant(cw_idx, currentUserId);
			
		} else {
			int currentCroomLimit = croom.getCroom_limit() != null ? croom.getCroom_limit() : 0;
			int maxCwLimit = cowork.getCw_limit() != null ? cowork.getCw_limit() : Integer.MAX_VALUE;

			Boolean isJoined = mapper.isUserInCroom(cw_idx, currentUserId);
			boolean userInCroom = (isJoined != null && isJoined);

	        // 이미 입장한 사용자는 항상 접근 가능
	        if (userInCroom) {
	            model.addAttribute("croom_info", croom);
	            model.addAttribute("isJoined", true);
	            return "find_detail";
	        }
	     // 입장 인원이 다 찼는지 확인
	        if (currentCroomLimit >= maxCwLimit) {
	        	model.addAttribute("message", "참여 인원이 다 찼습니다."); // alert에 표시할 메시지
	            model.addAttribute("redirectUrl", "/furture/find"); // 리다이렉션할 URL
	            return "alert"; // alert.jsp로 이동
	        }

	        // 입장 가능 시 인원 증가 및 참여자 추가
	        if (!userInCroom && currentCroomLimit < maxCwLimit) {
	        croom.setCroom_limit(currentCroomLimit + 1);
	        mapper.updateCroomLimit(croom);
	        mapper.addParticipant(cw_idx, currentUserId);
	    } else {
				model.addAttribute("limitReached", true);
			}
		}

		model.addAttribute("croom_info", croom);
		model.addAttribute("isJoined", mapper.isUserInCroom(cw_idx, currentUserId));
		return "find_detail";
	}


   // find 페이지 이동
   @GetMapping("/find")
   public String findPage(Model model) {
      System.out.println("find 페이지 이동");
      List<insert_cowork> coworkList = comapper.getCoworkList();
      for (insert_cowork cowork : coworkList) {
           croom_info croom = mapper.getCroomByCwIdx(cowork.getCw_idx());
           if (croom != null) {
               cowork.setCurrentParticipants(croom.getCroom_limit());
           } else {
               cowork.setCurrentParticipants(0);
           }
       }
      model.addAttribute("coworkList", coworkList);
      List<insert_feed> feedList = feedmapper.getFeedList();
      model.addAttribute("feedList", feedList);
      System.out.println(coworkList);
      return "find";
   }


}


