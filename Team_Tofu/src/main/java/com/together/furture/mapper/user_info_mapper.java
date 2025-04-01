package com.together.furture.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.together.furture.entity.user_info;

//root-context.xml에서 scan 될 수 있게 @Mapper 어노테이션 표기
//-> 자동으로 객체 생성
@Mapper
public interface user_info_mapper {

	void user_join(user_info user);
	
	user_info user_login(user_info user);

	void user_update(user_info user);

//	int user_delete(String user_pw, String user_id);
	
//	user_info user_delete(user_info user);
	//int profile_save(user_info user);

	int user_delete(user_info user);
	
}
