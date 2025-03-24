<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FIND</title>
    <link rel="stylesheet" href="find.css">
    <style type="text/css">
    	body {
	    font-family: Arial, sans-serif;
	    background-color: #fafafa;
	    margin: 0;
	    padding: 0;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    height: 100vh;
	}
	
	.find-container {
	    width: 800px; /* 컨테이너 크기 더 키움 */
	    background-color: #fff;
	    border-radius: 10px;
	    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	    overflow: hidden;
	    display: flex;
	    flex-direction: column;
	}
	
	.find-header {
	    background-color: #f8f8f8;
	    padding: 15px;
	    border-bottom: 1px solid #ddd;
	    display: flex;
	    align-items: center;
	}
	
	#back-button {
	    background: none;
	    border: none;
	    font-size: 24px;
	    cursor: pointer;
	    color: #000;
	    margin-right: 10px;
	}
	
	.find-header h2 {
	    margin: 0;
	    font-size: 18px;
	}
	
	.find-content {
	    padding: 20px;
	    display: grid;
	    grid-template-columns: repeat(2, 1fr); /* 2열 */
	    grid-template-rows: repeat(2, 1fr); /* 2행 */
	    gap: 20px; /* 박스 사이 간격 */
	}
	
	.room {
	    padding: 20px;
	    border: 1px solid #ddd;
	    border-radius: 10px;
	    background-color: #fff;
	    display: flex;
	    flex-direction: column;
	    justify-content: space-between;
	}
	
	.room-author {
	    display: flex;
	    align-items: center;
	    margin-bottom: 10px;
	}
	
	.room-author img {
	    width: 40px;
	    height: 40px;
	    border-radius: 50%;
	    margin-right: 10px;
	}
	
	.author-nickname {
	    font-size: 16px;
	    font-weight: bold;
	}
	
	.room h3 {
	    margin: 0 0 10px;
	    font-size: 24px; /* 제목 크기 더 키움 */
	}
	
	.room p {
	    margin: 0 0 10px;
	    color: #666;
	    font-size: 18px; /* 텍스트 크기 더 키움 */
	}
	
	.join-button {
	    padding: 15px 20px; /* 버튼 크기 키움 */
	    background-color: #000000; /* 검은색 */
	    color: #fff;
	    border: none;
	    border-radius: 5px;
	    cursor: pointer;
	    width: 100%;
	    font-size: 18px; /* 버튼 텍스트 크기 키움 */
	}
	
	.join-button:hover {
	    background-color: #525252; /* 호버 시 회색 */
	}
    </style>
</head>
<body>
    <div class="find-container">
        <div class="find-header">
        <form action="main.do">
            <button id="back-button">←</button>
        </form>
            <h2>FIND</h2>
        </div>
        <div class="find-content">
            <div class="room">
                <div class="room-author">
                    <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                    <span class="author-nickname">닉네임1</span>
                </div>
                <h3>함께 그림 작업 하실 분</h3>
                <p>2/7</p>
                <button class="join-button">참여하기</button>
            </div>
            <div class="room">
                <div class="room-author">
                    <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                    <span class="author-nickname">닉네임2</span>
                </div>
                <h3>함께 밴드 하실 분</h3>
                <p>2/5</p>
                <button class="join-button">참여하기</button>
            </div>
            <div class="room">
                <div class="room-author">
                    <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                    <span class="author-nickname">닉네임3</span>
                </div>
                <h3>유튜브 편집 하실 분</h3>
                <p>0/1</p>
                <button class="join-button">참여하기</button>
            </div>
            <div class="room">
                <div class="room-author">
                    <img src="https://via.placeholder.com/40" alt="프로필 이미지">
                    <span class="author-nickname">닉네임4</span>
                </div>
                <h3>공모전 함께 참가하실 분</h3>
                <p>2/3</p>
                <button class="join-button">참여하기</button>
            </div>
        </div>
    </div>
    <script src="find.js"></script>
</body>
</html>