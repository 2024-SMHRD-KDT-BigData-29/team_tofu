<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/group_chat.css">
</head>
<body>
	<body>
    <div class="chat-container">
        <div class="chat-header">
            <button id="back-button">←</button>
            <h2 id="chat-title" name="user_nick">홍길동</h2> <!-- 닉네임이 여기에 표시됩니다 -->
        </div>
        <div class="chat-messages" id="chat-messages">
            <!-- 메시지들이 여기에 표시됩니다 -->
        </div>
        <div class="chat-input">
            <input type="text" id="message-input" placeholder="메시지를 입력하세요...">
            <button id="send-button">전송</button>
        </div>
    </div>
    <script src="resources/js/group_chat.js"></script>
</body>
</html>