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
    private Integer feedIdx;

    // 글 제목 
    private String feedTitle;

    // 글 내용 
    private String feedContent;

    // 글 첨부파일 
    private String feedFile;

    // 글 작성일자 
    private Timestamp createdAt;

    // 글 조회수 
    private Integer feedViews;

    // 글 좋아요수 
    private Integer feedLikes;

    // 글 작성자 
    private String userId;

    // 해시 태그 
    private String hashTag;
}