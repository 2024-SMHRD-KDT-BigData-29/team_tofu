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
import com.together.furture.entity.user_info;
import com.together.furture.mapper.cowork_info_mapper;
import com.together.furture.mapper.croom_info_mapper;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;
import com.together.furture.util.coworkUploadUtil;

@Controller
public class croom_info_controller {
	@Autowired
	cowork_info_mapper comapper;
	@Autowired
	user_info_mapper user;
	@Autowired
	feed_info_mapper feedmapper;
	@Autowired
	private coworkUploadUtil coworkUploadUtil;
	@Autowired
	croom_info_mapper mapper;

	@RequestMapping("find_detail")
	public String find_detail(Model model) {
		System.out.println("페이지 구현중");
		List<insert_cowork> coworkList = comapper.getCoworkList();
		model.addAttribute("coworkList", coworkList);
		return "find_detail";
	}
	
	

}
