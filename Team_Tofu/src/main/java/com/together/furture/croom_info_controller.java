package com.together.furture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.together.furture.mapper.cowork_info_mapper;
import com.together.furture.mapper.croom_info_mapper;
import com.together.furture.mapper.feed_info_mapper;
import com.together.furture.mapper.user_info_mapper;
import com.together.furture.util.coworkUploadUtil;

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
	public String find_detail() {
		
		return "find_detail";
	}
	
}
