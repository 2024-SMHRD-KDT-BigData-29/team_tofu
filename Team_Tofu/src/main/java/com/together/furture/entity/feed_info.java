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
public class feed_info {

    // 글 식별자 
    private Integer feed_idx;

    // 글 제목 
    private String feed_title;

    // 글 내용 
    private String feed_content;

    // 글 첨부파일 
    private String feed_file;

    // 글 작성일자 
    private Timestamp created_at;

    // 글 조회수 
    private Integer feed_views;

    // 글 좋아요수 
    private Integer feed_likes;

    // 글 작성자 
    private String user_id;

    // 해시 태그 
    private String hash_tag;
}