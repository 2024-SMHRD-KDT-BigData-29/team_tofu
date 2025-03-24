<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="./mypage.css">
</head>
<body>
    <div class="profile-container">
        <div class="profile-header">
            <form action="main.do">
            	<button id="back-button">←</button>
        	</form>
            <h2>마이페이지</h2>
        </div>
        <div class="profile-content">
            <div class="profile-info">
                <h1 name="user_id">홍길동</h1>
                <p>동에 번쩍 서에 번쩍</p>
                <div class="profile-stats">
                    <div class="stat">
                        <span class="stat-number">120</span>
                        <span class="stat-label">팔로워</span>
                    </div>
                    <div class="stat">
                        <span class="stat-number">300</span>
                        <span class="stat-label">팔로잉</span>
                    </div>
                </div>
                <div class="profile-actions">
                    <button id="edit-profile-button">프로필 수정</button>
                </div>
            </div>
            <div class="profile-section">
                <h3>참여 중인 활동</h3>
                <div id="ongoing-activities">
                    <!-- 동적으로 활동 내용이 여기에 추가됩니다 -->
                </div>
            </div>
            <div class="profile-section">
                <h3>참여한 활동</h3>
                <p>내가 참여했던 활동이나 참여한 활동을 추가해 보세요</p>
                <button class="add-button">추가하기</button>
            </div>
            <div class="profile-section">
                <h3>내가 만든 작품</h3>
                <p>내가 만든 작품들을 추가해 보세요</p>
                <button class="add-button">추가하기</button>
            </div>
            <div class="profile-section">
                <h3>링크</h3>
                <p>SNS 나 블로그 등으로 나를 표현해 보세요</p>
                <button class="add-button">추가하기</button>
            </div>
        </div>
    </div>
    <script src="./mypage.js"></script>
</body>
</html>