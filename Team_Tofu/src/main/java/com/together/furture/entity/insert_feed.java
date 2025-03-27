package com.together.furture.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class insert_feed {
	
	@NonNull private String feed_title;
	
	@NonNull private String feed_content;
	
	private String feed_file;
	
	@NonNull private String hash_tag;
	
	private String user_id;
	
	private String user_nick;	
	
	
}
