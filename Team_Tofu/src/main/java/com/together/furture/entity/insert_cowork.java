package com.together.furture.entity;


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
public class insert_cowork {

    // 협업 제목 
    @NonNull private String cw_title;

    // 협업 소개 
    @NonNull private String cw_intro;

    // 협업 인원수 
    @NonNull private Integer cw_limit;
    
    // 협업 이미지 
    private String cw_img;
    
    // 협업 해시태그
    private String hash_tag;
    
    // 협업 작성자 
    private String user_id;
    
    // 협업 닉네임
    private String user_nick;
    
    
	
}
