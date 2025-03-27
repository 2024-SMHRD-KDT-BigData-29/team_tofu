package com.together.furture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.together.furture.entity.comment_info;
import com.together.furture.entity.comment_insert;
import com.together.furture.entity.user_info;

@Mapper
public interface comment_mapper {
	
	int comment_send(comment_insert cmt); // 댓글 저장
	
	user_info user_login(user_info user); // 로그인한 유저 정보 조회
	
	List<comment_insert> comment_show(); // 특정 피드의 댓글 조회
}
