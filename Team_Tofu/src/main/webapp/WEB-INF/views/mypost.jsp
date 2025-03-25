<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 보기</title>
    <link rel="stylesheet" href="resources/css/mypost.css">
</head>
<body>
	 <div class="post-container">
        <!-- 상단 바 -->
        <div class="post-header">
        	<form action="main.do">
            <button id="back-button">←</button>
            </form>
            <h2>게시글 보기</h2>
        </div>

        <!-- 게시글 내용 -->
        <div class="post-content">
            <div class="post-title">
                <div class="user-info">
                <img src="resources/img/${empty login_user.user_profile ? 'default_profile.png' : login_user.user_profile}" alt="프로필" class="profile-img">
                    <h1 name="user_nick">${login_user.user_nick}</h1>
                </div>
                <div id="post-content" class="post-text">${login_user.user_intro}</div>
            </div>
            <!-- 이전 게시글 목록 -->
            <div class="previous-posts">
                <h3>나의  게시글</h3>
	         	<div class="post-list" id="post-list">
	            
	            <!-- 이전 게시글 자동 로드 위치 -->
	        	</div>
            </div>
        </div>

        <!-- 하단 버튼 -->
        <div class="post-actions">
            <button id="delete-post-button">게시글 삭제</button>
        </div>
    </div>
</body>
</html>