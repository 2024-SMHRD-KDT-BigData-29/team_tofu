<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To.fu</title>
    <link rel="stylesheet" href="resources/css/main.css"/>
    
</head>
<body>
    <!-- 고정된 상단 바 -->
    <div id="top-bar">
        <div id="left-section">
            <span id="tofu-main">To.fu</span>
            <span id="find-main"><a href="find.do">FIND</a></span>
        </div>
        <form action="#" method="get">
            <input class="search-txt" type="text" placeholder="검색어를 입력해주세요">
            <button class="search-btn">🔍</button>
        </form>
        <form action="mypage.do" method="get">
            <button id="profile-bar">
                <img src="resources/images/profile.jpg" alt="프로필 이미지">
            </button>
        </form>
    </div>

    <!-- 스토리 섹션 -->
    <div id="story-section">
        <c:forEach var="i" begin="1" end="5">
            <div class="story">
                <img src="resources/images/story${i}.jpg" alt="프로필${i}">
                <span class="story-nickname">닉네임${i}</span>
            </div>
        </c:forEach>
    </div>

    <!-- 피드 섹션 -->
    <div id="feed-section">
        <!-- 태그 필터 섹션 -->
        <div class="tag-filter">
            <label class="tag-label">
                <input type="checkbox" name="tag" value="그림">
                <span>#그림</span>
            </label>
            <label class="tag-label">
                <input type="checkbox" name="tag" value="영상">
                <span>#영상</span>
            </label>
            <label class="tag-label">
                <input type="checkbox" name="tag" value="음악">
                <span>#음악</span>
            </label>
            <label class="tag-label">
                <input type="checkbox" name="tag" value="편집">
                <span>#편집</span>
            </label>
        </div>

        <!-- 게시글 목록 -->
        <c:forEach var="feed" items="${feedList}">
            <div class="post">
                <div class="post-header">
                    <img src="resources/images/profile.jpg" alt="닉네임 프로필" class="profile-img">
                    <span class="nickname" name="user_nick">${feed.user_nick}</span>
                    <span class="post-menu">&#8942;</span>
                </div>
                
                <div>
                    <span class="post-menu">${feed.feed_title}</span>
                    <p>${feed.feed_content}</p>
                    <img src="resources/uploads/${feed.feed_file}" alt="게시글 이미지" class="post-image">
                </div>
                
                <div class="post-actions">
                    <button class="like-btn">❤️</button>
                    <button class="comment-btn">💬</button>
                </div>
                
                <div class="post-caption">
                    <span class="nickname" name="user_nick">${feed.user_nick}</span>
                    <span class="caption">${feed.feed_content}</span>
                </div>
                
                <div class="comment-list">
                    <c:forEach var="comment" items="${feedList}">
                        <div class="comment-item">
                            <span class="comment-nickname">${comment.user_nick}</span>
                            <span class="comment-text">${comment.feed_content}</span>
                            <button class="comment-delete">🗑️</button>
                        </div>
                    </c:forEach>
                </div>
                
                <div class="comment-section">
                    <textarea class="comment-input" placeholder="댓글을 입력하세요..."></textarea>
                    <button class="comment-submit">게시</button>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- 상단 이동 버튼 및 게시물 작성 버튼 -->
    <div class="floating-buttons">
        <form method="get" action="write">
            <button id="write-post">✏️</button>
        </form>
        <button id="scroll-top">&#9650;</button>
    </div>