package com.together.furture.entity;

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
public class comment_insert {
	// 회원 닉네임
	private String user_nick;
	
    // 댓글 내용 
    private String cmt_content;

    // 댓글 작성자 
    private String user_id;
    
    private Integer feed_idx; 

    
}
