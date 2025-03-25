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
	    private Integer cwIdx;

	    // 협업 제목 
	    private String cwTitle;

	    // 협업 소개 
	    private String cwIntro;

	    // 협업 인원수 
	    private Integer cwLimit;

	    // 협업 작성자 
	    private String userId;

	    // 협업 마감일자 
	    private Timestamp closedAt;

	    // 협업 이미지 
	    private String cwImg;

	    // 협업 작성일자 
	    private Timestamp createdAt;


}
