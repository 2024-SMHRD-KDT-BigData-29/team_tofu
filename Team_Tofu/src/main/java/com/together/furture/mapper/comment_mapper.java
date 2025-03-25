package com.together.furture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.together.furture.entity.comment_info;

@Mapper
public interface comment_mapper {
	
	void comment_send(comment_info cmt); // 댓글 저장
	
	List<comment_info> comment_show(String user_id); // 댓글 조회
}
