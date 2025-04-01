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
	    private Integer croom_idx;

	    // 협업 식별자 
	    private Integer cw_idx;

	    // 방 제목 
	    private String croom_title;

	    // 방 소개 
	    private String croom_info;

	    // 방 이미지 
	    private String croom_img;

	    // 방 인원수 
	    private Integer croom_limit;

	    // 방 개설일자 
	    private Timestamp created_at;

	    // 방 개설자 
	    private String user_id;

	    // 방 상태 
	    private String croom_status;

		
}
