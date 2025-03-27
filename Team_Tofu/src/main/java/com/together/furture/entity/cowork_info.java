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
public class cowork_info {


	    // 협업 식별자 
	    private Integer cw_idx;

	    // 협업 제목 
	    private String cw_title;

	    // 협업 소개 
	    private String cw_intro;

	    // 협업 인원수 
	    private Integer cw_limit;

	    // 협업 작성자 
	    private String user_id;
	    
	    // 협업 이미지 
	    private String cw_img;
	    
	    // 유저 닉네임
	    private String user_nick;


}
