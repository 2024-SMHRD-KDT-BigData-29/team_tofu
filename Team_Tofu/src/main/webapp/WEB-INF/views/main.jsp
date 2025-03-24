<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To.fu</title>
    <link rel="stylesheet" href="./Main.css" />
    <style type="text/css">
    * {
    padding: 0;
    margin: 0;
    border: none;
    box-sizing: border-box;
	}
	
	body {
	    font-size: 14px;
	    font-family: 'Roboto', sans-serif;
	    background-color: #fafafa;
	}
	
	/* 고정된 상단 바 */
	#top-bar {
	    height: 60px;
	    border-bottom: 1px solid rgb(173, 173, 173);
	    display: flex;
	    align-items: center;
	    padding: 0 15px;
	    background-color: #fff;
	    position: fixed;
	    top: 0;
	    left: 0;
	    right: 0;
	    z-index: 1000;
	    flex-wrap: nowrap; /* 줄바꿈 방지 */
	}
	
	#tofu-main {
	    font-size: 24px;
	    font-weight: bold;
	    padding: 0 10px;
	    margin-right: 20px;
	    flex-shrink: 0;
	    white-space: nowrap;
	}
	
	#find-main {
	    font-size: 20px;
	    font-weight: bold;
	    color: #c5c5c5;
	    cursor: pointer;
	    flex-shrink: 0;
	    white-space: nowrap;
	}
	
	#top-bar form {
	    display: flex;
	    align-items: center;
	    gap: 5px;
	    margin-left: auto;
	    flex-shrink: 1;
	    min-width: 200px;
	}
	
	.search-txt {
	    height: 40px;
	    width: 100%;
	    max-width: 300px;
	    border: 1px solid #c5c5c5;
	    border-radius: 10px;
	    padding: 5px;
	}
	
	.search-btn {
	    height: 40px;
	    width: 40px;
	    border-radius: 50%;
	    border: none;
	    background-color: #c5c5c5;
	    color: white;
	    cursor: pointer;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    font-size: 16px;
	}
	
	#profile-bar {
	    height: 40px;
	    width: 40px;
	    background-color: black;
	    border-radius: 50%;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    cursor: pointer;
	    margin-left: 10px;
	    flex-shrink: 0;
	    white-space: nowrap;
	}
	
	/* 스토리 섹션 */
	#story-section {
	    display: flex;
	    justify-content: center; /* 가운데 정렬 */
	    overflow-x: auto;
	    padding: 10px;
	    gap: 10px;
	    white-space: nowrap;
	    margin-top: 60px; /* 상단 바 높이만큼 여백 추가 */
	    background-color: #fff;
	    max-width: 800px; /* 최대 너비 설정 */
	    margin-left: auto; /* 가운데 정렬 */
	    margin-right: auto; /* 가운데 정렬 */
	}
	/* 스토리 상자 스타일*/
	.story {
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    width: 80px; /* 상자 너비 */
	    height: 100px; /* 상자 높이 */
	    margin-top: 10px;
	    border: 1px solid #c5c5c5; /* 테두리 추가 */
	    border-radius: 10px; /* 둥근 모서리 */
	    padding: 5px; /* 내부 여백 */
	    background-color: #fff; /* 배경색 */
	    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	}
	
	.story img {
	    width: 60px; /* 이미지 크기 조정 */
	    height: 60px;
	    object-fit: cover;
	    border-radius: 50%;
	    border: 2px solid #c5c5c5;
	}
	.story span {
	    margin-top: 5px; /* 텍스트와 이미지 사이 간격 */
	    font-size: 12px; /* 텍스트 크기 */
	    text-align: center; /* 텍스트 가운데 정렬 */
	}
	.story-nickname {
	    margin-top: 5px; /* 이미지와 텍스트 사이 간격 */
	    font-size: 12px; /* 텍스트 크기 */
	    text-align: center; /* 텍스트 가운데 정렬 */
	    white-space: nowrap; /* 텍스트 줄바꿈 방지 */
	    overflow: hidden; /* 넘치는 텍스트 숨기기 */
	    text-overflow: ellipsis; /* 넘치는 텍스트를 ...로 표시 */
	    max-width: 70px; /* 최대 너비 설정 */
	}
	
	
	/* 피드 섹션 */
	#feed-section {
	    margin-top: 20px;
	    padding: 10px;
	    background-color: #fff;
	    max-width: 800px; /* 최대 너비 설정 */
	    margin-left: auto; /* 가운데 정렬 */
	    margin-right: auto; /* 가운데 정렬 */
	}
	
	.post {
	    border: 1px solid #c5c5c5;
	    border-radius: 10px;
	    margin-bottom: 20px;
	    background-color: #fff;
	    padding: 10px; /* 내부 여백 추가 */
	    margin-left: 10px; /* 왼쪽 여백 추가 */
	    margin-right: 10px; /* 오른쪽 여백 추가 */
	}
	.post-header {
	    display: flex;
	    align-items: center;
	    padding: 10px;
	}
	
	.profile-img {
	    width: 40px;
	    height: 40px;
	    border-radius: 50%;
	    margin-right: 10px;
	}
	
	.nickname {
	    font-size: 14px;
	    font-weight: bold;
	}
	
	.post-menu {
	    margin-left: auto;
	    cursor: pointer;
	    font-size: 20px;
	}
	
	.post-image {
	    width: 100%;
	    display: block;
	}
	
	.post-actions {
	    padding: 10px;
	    display: flex;
	    gap: 10px;
	}
	
	.like-btn, .comment-btn {
	    background: none;
	    border: none;
	    font-size: 20px;
	    cursor: pointer;
	}
	
	.post-caption {
	    padding: 0 10px 10px;
	    font-size: 14px;
	    
	}
	
	.post-caption .nickname {
	    font-weight: bold;
	    margin-right: 5px;
	}
	
	
	/*태그 섹션*/
	/* 태그 필터 스타일 */
	.tag-filter {
	    display: flex;
	    gap: 10px; /* 태그 사이 간격 */
	    padding: 10px; /* 내부 여백 */
	    margin-bottom: 20px; /* 게시글과의 간격 */
	    background-color: #fff; /* 배경색 */
	    
	}
	
	/* 체크박스 숨기기 */
	.tag-filter input[type="checkbox"] {
	    display: none; /* 체크박스 숨기기 */
	}
	
	/* 태그 라벨 스타일 */
	.tag-label {
	    display: inline-block;
	    padding: 5px 10px;
	    border: 1px solid #c5c5c5; /* 테두리 */
	    border-radius: 15px; /* 둥근 모서리 */
	    font-size: 14px;
	    cursor: pointer;
	    transition: background-color 0.3s, border-color 0.3s; /* 부드러운 전환 효과 */
	}
	
	/* 태그 선택 시 스타일 */
	/* 태그 선택 시 스타일 */
	.tag-label input[type="checkbox"]:checked + span {
	    
	    
	    border-color: #000; /* 테두리 색상 변경 */
	    font-weight: 1000; /* 글씨 두껍게 */
	}
	/* 댓글 작성 섹션 스타일 */
	/* 댓글 목록 스타일 */
	.comment-list {
	    padding: 10px;
	    border-top: 1px solid #c5c5c5; /* 상단 경계선 */
	    margin-bottom: 10px; /* 댓글 작성 섹션과의 간격 */
	}
	
	.comment-item {
	    display: flex;
	    align-items: center;
	    margin-bottom: 5px; /* 댓글 간 간격 */
	    font-size: 14px;
	}
	
	.comment-nickname {
	    font-weight: bold; /* 닉네임 두껍게 */
	    margin-right: 5px; /* 닉네임과 텍스트 사이 간격 */
	}
	
	/* 댓글 작성 섹션 스타일 */
	.comment-section {
	    padding: 10px;
	    border-top: 1px solid #c5c5c5; /* 상단 경계선 */
	    display: flex;
	    align-items: flex-end; /* 하단 정렬 */
	    gap: 10px; /* 입력란과 버튼 사이 간격 */
	}
	
	.comment-input {
	    flex: 1; /* 남은 공간을 모두 차지 */
	    height: 60px;
	    padding: 10px;
	    border: 1px solid #c5c5c5;
	    border-radius: 10px;
	    font-family: 'Roboto', sans-serif;
	    font-size: 14px;
	    resize: vertical; /* 세로로만 크기 조정 가능 */
	    outline: none; /* 포커스 시 테두리 제거 */
	}
	
	.comment-submit {
	    padding: 5px 10px;
	    background-color: #000;
	    color: #fff;
	    border: none;
	    border-radius: 5px;
	    font-size: 14px;
	    cursor: pointer;
	}
	
	.comment-submit:hover {
	    background-color: #333; /* 호버 시 배경색 변경 */
	}
	.comment-delete {
	    margin-left: auto; /* 오른쪽으로 이동 */
	    background: none;
	    border: none;
	    cursor: pointer;
	    font-size: 14px;
	}
	/* 플로팅 버튼 컨테이너 */
	.floating-buttons {
	    position: fixed;
	    bottom: 20px;
	    right: 20px;
	    display: flex;
	    gap: 10px; /* 버튼 사이 간격 */
	}
	
	/* 게시물 작성 버튼 스타일 */
	#write-post {
	    background: black;
	    color: white;
	    border: none;
	    width: 40px; /* 버튼 너비 */
	    height: 40px;
	    border-radius: 20px; /* 둥근 모서리 */
	    font-size: 14px;
	    cursor: pointer;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    gap: 5px; /* 아이콘과 텍스트 사이 간격 */
	    margin-right: 20px; /* 상단으로 가는 버튼과의 간격 */
	}
	
	#write-post:hover {
	    background: #333; /* 호버 시 배경색 변경 */
	}
	
	/* 맨 위로 버튼 스타일 */
	#scroll-top {
	    background: black;
	    color: white;
	    border: none;
	    width: 40px;
	    height: 40px;
	    border-radius: 50%;
	    font-size: 20px;
	    cursor: pointer;
	}
	
	#scroll-top:hover {
	    background: #333; /* 호버 시 배경색 변경 */
	}
	/* 스토리 클릭 시 눌러지는 모션 */
	.story {
	    transition: transform 0.1s ease; /* 부드러운 변환 효과 */
	}
	
	.story:active {
	    transform: scale(0.95); /* 클릭 시 약간 축소 */
	}
    </style>
</head>
<body>
    <!-- 고정된 상단 바 -->
    <div id="top-bar">
        <span id="tofu-main">To.fu</span>
        <span id="find-main"><a>FIND</a></span>
        <form action="#" method="post">
            <input class="search-txt" type="text" placeholder="검색어를 입력해주세요">
            <button class="search-btn"></button>
        </form>
        <button id="profile-bar"></button>
    </div>

    <!-- 스토리 섹션 -->
  
    <div id="story-section">
        <div class="story">
            <img src="./이미지/LOGIN.jpg" alt="프로필1">
            <span class="story-nickname">닉네임1</span>
        </div>
        <div class="story">
            <img src="./이미지/FIND DETAIL.jpg" alt="프로필2">
            <span class="story-nickname">닉네임2</span>
        </div>
        <div class="story">
            <img src="./이미지/FIND.jpg" alt="프로필3">
            <span class="story-nickname">닉네임3</span>
        </div>
        <div class="story">
            <img src="./이미지/LOGIN.jpg" alt="프로필4">
            <span class="story-nickname">닉네임4</span>
        </div>
        <div class="story">
            <img src="./이미지/MY MENU.jpg" alt="프로필5">
            <span class="story-nickname">닉네임5</span>
        </div>
    </div>

   <!-- 피드 섹션 -->
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

    <!-- 게시글 1 -->
    <div class="post">
        <div class="post-header">
            <img src="./이미지/WRITE.jpg" alt="닉네임 프로필" class="profile-img">
            <span class="nickname" name="user_nick">닉네임1</span>
            <span class="post-menu">&#8942;</span> <!-- ⋮ 아이콘 -->
        </div>
        <img src="./이미지/MY POST.jpg" alt="게시글 이미지" class="post-image">
        <div class="post-actions">
            <button class="like-btn">❤️</button>
            <button class="comment-btn">💬</button>
        </div>
        <div class="post-caption">
            <span class="nickname" name="user_nick">닉네임1</span>
            <span class="caption">이것은 게시글 설명입니다.</span>
        </div>
        <div class="comment-list">
            <!-- 댓글 예시 -->
            <div class="comment-item">
                <span class="comment-nickname">닉네임1</span>
                <span class="comment-text">첫 번째 댓글입니다.</span>
                <button class="comment-delete">🗑️</button> <!-- 삭제 버튼 -->
            </div>
            <div class="comment-item">
                <span class="comment-nickname">닉네임2</span>
                <span class="comment-text">두 번째 댓글입니다.</span>
                <button class="comment-delete">🗑️</button> <!-- 삭제 버튼 -->
            </div>
        </div>
         <!-- 댓글 작성 섹션 -->
         <div class="comment-section">
            <textarea class="comment-input" placeholder="댓글을 입력하세요..."></textarea>
            <button class="comment-submit">게시</button>
        </div>
    </div>

    <!-- 게시글 2 -->
    <div class="post">
        <div class="post-header">
            <img src="./이미지/WRITE.jpg" alt="닉네임 프로필" class="profile-img">
            <span class="nickname" name="user_nick">닉네임2</span>
            <span class="post-menu">&#8942;</span> <!-- ⋮ 아이콘 -->
        </div>
        <img src="./이미지/MY POST.jpg" alt="게시글 이미지" class="post-image">
        <div class="post-actions">
            <button class="like-btn">❤️</button>
            <button class="comment-btn">💬</button>
        </div>
        <div class="post-caption">
            <span class="nickname" name="user_nick">닉네임2</span>
            <span class="caption">또 다른 게시글 설명입니다.</span>
        </div>
        <div class="comment-list">
            <!-- 댓글 예시 -->
            <div class="comment-item">
                <span class="comment-nickname">닉네임1</span>
                <span class="comment-text">첫 번째 댓글입니다.</span>
                <button class="comment-delete">🗑️</button> <!-- 삭제 버튼 -->
            </div>
            <div class="comment-item">
                <span class="comment-nickname">닉네임2</span>
                <span class="comment-text">두 번째 댓글입니다.</span>
                <button class="comment-delete">🗑️</button> <!-- 삭제 버튼 -->
            </div>
        </div>
         <!-- 댓글 작성 섹션 -->
         <div class="comment-section">
            <textarea class="comment-input" placeholder="댓글을 입력하세요..."></textarea>
            <button class="comment-submit">게시</button>
        </div>
    </div>

    
    <!-- 상단 이동 버튼 및 게시물 작성 버튼 -->
<div class="floating-buttons">
    <button id="write-post">✏️ </button>
    <button id="scroll-top">&#9650;</button>
</div>
    <script src="./Main.js"></script>
    
</body>
</html>