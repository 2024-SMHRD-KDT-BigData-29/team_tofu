<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>개인 메시지</title>
<link rel="stylesheet" href="resources/css/personal_msg.css">
</head>
<body>

	<div class="chat-container">
		<div class="chat-header">
			<button id="back-button" onclick="history.back()">←</button>
			<h2 id="chat-title">${receiver_nick}님과의채팅</h2>
		</div>

		<div class="chat-messages" id="chat-messages">
			<c:forEach var="msg" items="${chatHistory}">
				<div
					class="message ${msg.sender_id == login_user.user_id ? 'user' : 'other'}">
					<b>${msg.sender_id}</b>: ${msg.p_msg_content}
				</div>
			</c:forEach>
		</div>

		<!-- ✅ 엔터 전송을 위해 form으로 감싸고 기본 제출 막기 -->
		<form id="chat-form" onsubmit="return false;">
			<div class="chat-input">
				<input type="text" id="message-input" placeholder="메시지를 입력하세요..."
					autocomplete="off">
				<button type="submit" id="send-button">전송</button>
			</div>
		</form>
	</div>

	<!-- 현재 로그인 사용자와 상대방 정보 전달 -->
	<script>
		sessionStorage.setItem("sender_id", "${login_user.user_id}");
		sessionStorage.setItem("receiver_id", "${receiver_id}");
	</script>
	<c:if test="${empty login_user}">
		<script>
			alert("로그인 후 이용해주세요.");
			location.href = "/";
		</script>
	</c:if>
	<script>
		// 페이지 로드 완료 후, 스크롤을 맨 아래로 이동
		window.onload = function() {
			const chatBox = document.getElementById("chat-messages");
			chatBox.scrollTop = chatBox.scrollHeight;
		};
		window.addEventListener("beforeunload", function () {
			sessionStorage.setItem("chatJustRead", "true");
		});
		window.addEventListener("beforeunload", function () {
			sessionStorage.setItem("chatJustRead", "true");
		});
	</script>
	<script src="resources/js/personal_msg.js"></script>
</body>
</html>
