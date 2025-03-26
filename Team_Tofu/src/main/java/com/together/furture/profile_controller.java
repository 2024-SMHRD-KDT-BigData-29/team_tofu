package com.together.furture;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.together.furture.entity.user_info;
import com.together.furture.mapper.profile_mapper;
import com.together.furture.mapper.user_info_mapper;

@Controller
public class profile_controller {
	@Autowired
	user_info_mapper mapper;
	
	// 프로필 사진 저장
		//@PostMapping("/user_join.do")
		public String profile_save(user_info user, HttpServletRequest request, Object user_profile) {
			
			// 1. 요청객체 -> request
			// 2. 이미지 저장할 폴더의 경로(String)
			String save_path = request.getRealPath("resources/img");
			System.out.println("이미지 저장 경로 : " + save_path);
			
			// 3. 이미지 용량 크기(int)
			int maxSize = 1024 * 1024 * 10;
					
			// 4. 파일명 인코딩 방식(String)
			String encoding = "UTF-8";
			
			// 5. 파일명 중복제거(DefaultFileRenamePolicy)
			DefaultFileRenamePolicy rename = new DefaultFileRenamePolicy();
			
			MultipartRequest multi = null;
			
			try {
				multi = new MultipartRequest(request, save_path, maxSize, encoding, rename);
				
				String profile_name = multi.getParameter("user_profile");
				
				
				System.out.println(user.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "redirect:/goBoardMain";
		}
	
}
