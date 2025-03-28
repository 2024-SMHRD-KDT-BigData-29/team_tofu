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
public class croom_info {

	    // 방 식별자 
	    private Integer croomIdx;

	    // 협업 식별자 
	    private Integer cwIdx;

	    // 방 제목 
	    private String croomTitle;

	    // 방 소개 
	    private String croomInfo;

	    // 방 이미지 
	    private String croomImg;

	    // 방 인원수 
	    private Integer croomLimit;

	    // 방 개설일자 
	    private Timestamp createdAt;

	    // 방 개설자 
	    private String userId;

	    // 방 상태 
	    private String croomStatus;
}
