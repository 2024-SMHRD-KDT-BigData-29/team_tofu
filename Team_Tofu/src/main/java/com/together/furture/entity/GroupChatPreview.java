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
public class GroupChatPreview {
	private int croom_idx;
	private String croom_title;
	private String last_msg;
	private Timestamp sended_at;
	private Integer unread_count;
}
