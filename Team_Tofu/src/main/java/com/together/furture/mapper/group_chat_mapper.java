package com.together.furture.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.together.furture.entity.group_chat;
import com.together.furture.entity.user_info;

@Mapper
public interface group_chat_mapper {

	// 그룹 채팅 메시지 저장
	void insert(group_chat chat); // ✔ insert 하나만 유지

	// 특정 방의 메시지 전체 조회
	List<group_chat> select_by_croom_idx(@Param("croom_idx") int croom_idx); // ✔ 이름 명확히

	// 참여자 조회 (닉네임 포함된 user_info)
	List<user_info> getParticipantsByCwIdx(@Param("cw_idx") int cw_idx);
}
