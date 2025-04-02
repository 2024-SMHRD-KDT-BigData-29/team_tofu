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

		<!-- 회의록 저장 버튼 -->
		<div class="chat-download">
			<form action="group_chat/download" method="get">
				<input type="hidden" name="croom_idx"
					value="${croom_info.croom_idx}">
				<button type="submit">📄 회의록 저장</button>
			</form>
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

	<script>
	function goBack() {
		sessionStorage.setItem("groupChatJustRead", "true"); // ✅ 알림 제거 플래그 저장
		window.history.back();
	}

	window.onload = () => {
		const chatBox = document.getElementById("group-chat-messages");
		const croom_idx = document.getElementById("group-chat-box").getAttribute("data-croom-idx");
		
		fetch("chat/messages?croom_idx=" + croom_idx)
			.then(res => res.json())
			.then(list => {
				list.forEach(msg => appendMessage(msg));

				// ✅ DOM 업데이트 완료 후 스크롤 이동
				setTimeout(() => {
					chatBox.scrollTop = chatBox.scrollHeight;
					console.log("✅ 그룹채팅 스크롤 맨 아래 이동 완료");
				}, 0);
			})
			.catch(err => {
				console.error("❌ 메시지 로딩 실패:", err);
			});
	};
</script>
	<script src="resources/js/group_chat.js"></script>

</body>
</html>
