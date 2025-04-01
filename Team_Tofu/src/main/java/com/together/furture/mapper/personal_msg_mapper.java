package com.together.furture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.together.furture.entity.ChatPreview;
import com.together.furture.entity.personal_msg;

@Mapper
public interface personal_msg_mapper<ChatPreviewDTO> {
	void insertMessage(personal_msg msg);

	List<personal_msg> getMessagesBetween(@Param("sender_id") String sender_id,
			@Param("receiver_id") String receiver_id);

	void insert_msg(personal_msg msg);

	int countUnreadMessages(String userId);

	List<ChatPreview> getChatPreviewList(String userId);

	List<personal_msg> getChatHistory(@Param("user1") String user1, @Param("user2") String user2);

	

}
