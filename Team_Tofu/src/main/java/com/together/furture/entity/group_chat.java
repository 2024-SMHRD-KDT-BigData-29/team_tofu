package com.together.furture.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class group_chat {
	private int chat_idx;
	private int croom_idx;
	private String chatter;
	private String chat_content;
	private String chat_emoticon;
	private String chat_file;
	private Timestamp created_at;
}
