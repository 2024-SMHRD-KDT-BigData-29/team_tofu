<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <span id="tofu-main"><a href="main.do">To.fu</a></span>
            <span id="find-main"><a href="find.do">FIND</a></span>
        </div>
        <form action="#" method="get">
            <input class="search-txt" type="text" placeholder="검색어를 입력해주세요">
            <button class="search-btn"></button>
        </form>
        <button id="profile-btn" type="button">
        	<img src="${login_user.user_profile}" alt ="프로필 이미지" id="profile-bar">
       	</button>
        
    </div>

    <!-- 스토리 섹션 -->
    <div id="story-section">
        <div class="story">
            <img src="${pageContext.request.contextPath}/resources/images/LOGIN.jpg" alt="프로필1">
            <span class="story-nickname">닉네임1</span>
        </div>
        <div class="story">
            <img src="${pageContext.request.contextPath}/resources/images/FIND_DETAIL.jpg" alt="프로필2">
            <span class="story-nickname">닉네임2</span>
        </div>
        <div class="story">
            <img src="${pageContext.request.contextPath}/resources/images/FIND.jpg" alt="프로필3">
            <span class="story-nickname">닉네임3</span>
        </div>
        <div class="story">
            <img src="${pageContext.request.contextPath}/resources/images/LOGIN.jpg" alt="프로필4">
            <span class="story-nickname">닉네임4</span>
        </div>
        <div class="story">
            <img src="${pageContext.request.contextPath}/resources/images/MY_MENU.jpg" alt="프로필5">
            <span class="story-nickname">닉네임5</span>
        </div>
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

        <!-- 게시글 1 -->
        <div class="post">
            <div class="post-header">
                <img src="${pageContext.request.contextPath}/resources/images/WRITE.jpg" alt="닉네임 프로필" class="profile-img">
                <span class="nickname" name="user_nick">닉네임1</span>
                <span class="post-menu">&#8942;</span> <!-- ⋮ 아이콘 -->
            </div>
            <img src="${pageContext.request.contextPath}/resources/images/MY_POST.jpg" alt="게시글 이미지" class="post-image">
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
                <img src="${pageContext.request.contextPath}/resources/images/WRITE.jpg" alt="닉네임 프로필" class="profile-img">
                <span class="nickname" name="user_nick">닉네임2</span>
                <span class="post-menu">&#8942;</span> <!-- ⋮ 아이콘 -->
            </div>
            <img src="${pageContext.request.contextPath}/resources/images/MY_POST.jpg" alt="게시글 이미지" class="post-image">
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
    </div>

    <!-- 상단 이동 버튼 및 게시물 작성 버튼 -->
    <div class="floating-buttons">
        <button id="write-post">✏️</button>
        <button id="scroll-top">&#9650;</button>
    </div>
    
    <!-- 팝업 오버레이 -->
    <div id="popup-overlay"></div>
    <script>
    	let user_profile = "${login_user.user_profile}"
    	let user_nick = "${login_user.user_nick}"
    	let user_intro = "${login_user.user_intro}";
    
    </script>
    <script src="resources/js/main.js"></script>
    
</body>
</html>