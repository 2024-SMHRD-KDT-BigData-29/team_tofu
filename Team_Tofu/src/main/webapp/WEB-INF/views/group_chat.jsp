<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹 채팅</title>
<link rel="stylesheet" href="resources/css/group_chat.css">
</head>
<body>

	<div class="group-chat-container">
		<h2 class="chat-title">그룹 채팅방</h2>

		<!-- ✅ 채팅 메시지 박스 + 사용자 정보 전달 -->
		<div id="group-chat-box" class="chat-box"
			data-croom-idx="${croom_info.croom_idx}"
			data-chatter="${login_user.user_id}">

			<!-- ✅ 기존 메시지 출력 영역 -->
			<div id="group-chat-messages" class="group-chat-messages">
				<c:forEach var="msg" items="${chatMessages}">
					<div
						class="chat-message ${msg.chatter eq login_user.user_id ? 'my-message' : 'other-message'}">
						<span class="chatter">${msg.chatter}</span>: <span>${msg.chat_content}</span>
					</div>
				</c:forEach>
			</div>

			<!-- ✅ 입력창 + 버튼 -->
			<div class="group-chat-input">
				<input type="text" id="group-message-input" placeholder="메시지를 입력하세요" />
				<button id="group-send-button">전송</button>
			</div>

		</div>
	</div>

	<script src="resources/js/group_chat.js"></script>
</body>
</html>
