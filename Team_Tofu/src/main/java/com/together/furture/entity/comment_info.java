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
public class comment_info {
	// 회원 닉네임
	private String user_nick;
	
	// 댓글 식별자
	private Integer cmt_idx;

    // 글 식별자 
    private Integer feed_idx;

    // 댓글 내용 
    private String cmt_content;

    // 댓글 작성일자 
    private Timestamp created_at;

    // 댓글 좋아요 
    private Integer cmt_like;

    // 댓글 작성자 
    private String user_id;
    
    
}
