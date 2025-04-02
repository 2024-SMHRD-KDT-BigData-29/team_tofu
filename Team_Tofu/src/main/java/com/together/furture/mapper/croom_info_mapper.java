package com.together.furture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.together.furture.entity.cowork_info;
import com.together.furture.entity.croom_info;
import com.together.furture.entity.insert_cowork;
import com.together.furture.entity.user_info;

@Mapper
public interface croom_info_mapper {

    // 협업방 정보
    croom_info cowork(croom_info cowork);
    
    // 특정 협업방 정보 조회
    insert_cowork getCoworkById(int coworkId);

    void update_croom(croom_info croom);

    croom_info getcw_idx(cowork_info cw_idx);

    croom_info getCroomByCwIdx(int cw_idx);

    void updateCroomLimit(croom_info croom);

    void createCroom(croom_info croom);
    
    void addParticipant(@Param("cw_idx") int cw_idx, @Param("user_id") String user_id); // 참여자 추가
    
    boolean isUserInCroom(@Param("cw_idx") int cw_idx, @Param("user_id") String user_id); // 참여 여부 확인
    
    List<user_info> getParticipantsByCwIdx(int croom_idx);
}