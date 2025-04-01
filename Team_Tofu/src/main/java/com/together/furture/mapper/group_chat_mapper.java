package com.together.furture.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.together.furture.entity.group_chat;

@Mapper
public interface group_chat_mapper {

	// 채팅 메시지 저장
	void insertChatMessage(group_chat chat);

	// 특정 채팅방의 메시지 가져오기
	List<group_chat> getChatMessages(int croom_idx);

	List<group_chat> select_by_croom_idx(int croom_idx);

	void insert(group_chat chat);
}
