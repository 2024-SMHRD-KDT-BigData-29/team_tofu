package com.together.furture.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class ChatPreview {
	private String user_id;
	private String last_msg;
	private Timestamp sended_at; // 또는 String
	private int unread_count;
	private String user_nick;
	private String user_profile;
}
