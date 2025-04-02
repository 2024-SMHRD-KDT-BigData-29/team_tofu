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
    
    // 협업 내용
    @NonNull private String cw_content;
    
    // 협업 이미지
    private String cw_img;
    
    // 협업 인원수 
    @NonNull private Integer cw_limit;
    
    // 협업 해시태그
    private String hash_tag;
    
    // 협업 작성자 
    private String user_id;
    
    // 협업 닉네임
    private String user_nick;
    
    // 협업 식별자
    private Integer cw_idx;
    

    // 실제 참여자 
    private Integer currentParticipants;
    
    @Override
    public String toString() {
        return "insert_cowork(cw_idx=" + cw_idx + ", cw_title=" + cw_title + ", cw_intro=" + cw_intro + 
               ", cw_content=" + cw_content + ", cw_img=" + cw_img + ", cw_limit=" + cw_limit + 
               ", hash_tag=" + hash_tag + ", user_id=" + user_id + ", user_nick=" + user_nick + 
               ", currentParticipants=" + currentParticipants + ")";
    }

    
	
}
