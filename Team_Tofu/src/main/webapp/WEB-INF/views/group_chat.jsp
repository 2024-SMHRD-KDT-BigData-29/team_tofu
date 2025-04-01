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
	<div class="chat-container">
		<!-- 헤더 -->
		<div class="chat-header">
			<button id="back-button" onclick="goBack()">←</button>
			<h2 class="chat-title">그룹 채팅방</h2>
		</div>

		<!-- 참여자 -->
		<div class="chat-participants">
			<strong>참여자:</strong>
			<c:forEach var="p" items="${participants}">
				<span class="participant">${p.user_nick}</span>
			</c:forEach>
		</div>

		<!-- 채팅 내용 -->
		<div class="chat-box" id="group-chat-box"
			data-croom-idx="${croom_info.croom_idx}"
			data-chatter="${login_user.user_id}">
			<div class="chat-messages" id="group-chat-messages">
				<c:forEach var="msg" items="${chatMessages}">
					<div
						class="message ${msg.chatter eq login_user.user_id ? 'user' : 'other'}">
						<span class="nickname">${msg.chatter}</span>
						<div class="bubble">${msg.chat_content}</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- 입력창 -->
		<div class="chat-input">
			<input type="text" id="group-message-input"
				placeholder="메시지를 입력하세요...">
			<button id="group-send-button">전송</button>
		</div>
	</div>

	<script src="resources/js/group_chat.js"></script>
	<script>
		function goBack() {
			window.history.back();
		}
		window.onload = () => {
			fetch("chat/messages?croom_idx=" + croom_idx)
				.then(res => res.json())
				.then(list => {
					list.forEach(msg => appendMessage(msg));
					// ✅ 불러오고 나서도 맨 아래로
					const box = document.getElementById("group-chat-messages");
					box.scrollTop = box.scrollHeight;
				})
				.catch(err => console.error("❌ 채팅 불러오기 실패", err));
		};
	</script>

</body>
</html>