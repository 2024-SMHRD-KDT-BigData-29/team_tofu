package com.together.furture.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.together.furture.entity.croom_info;
import com.together.furture.entity.insert_cowork;



@Mapper
public interface croom_info_mapper {
	// 특정 협업방의 참여자 추가
    void addParticipant(int coworkId, String userId);
    
    // 협업방 정보
    croom_info cowork(croom_info cowork);

    // 특정 협업방 정보 조회
    insert_cowork getCoworkById(int coworkId);

    // 특정 협업방의 참여자 수 조회 (선택적)
    int getParticipantCount(int coworkId);
    
}

