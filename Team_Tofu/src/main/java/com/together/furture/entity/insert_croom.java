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
public class insert_croom {
	
	// 방 제목 
    private String croom_title;
	
	// 방 개설자 
    private String user_id;
    
    // 방 이미지 
    private String croom_img;

}
