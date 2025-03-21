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

// 유저 정보
public class user_info {
	// 회원 아이디 
    private String user_id;

    // 회원 비밀번호 
    private String user_pw;

    // 회원 닉네임 
    private String user_nick;

    // 회원 성별 
    private String user_gender;

    // 회원 이메일 
    private String user_email;

    // 회원 한줄소개 
    private String user_intro;

    // 회원 프로필사진 
    private String user_profile;

    // 회원 구분 
    private String user_role;

    // 가입 일자 
    private Timestamp joined_at;
	
	
}
