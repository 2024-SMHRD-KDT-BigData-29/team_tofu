<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>채팅 목록</title>
<link rel="stylesheet" href="resources/css/my_msg_list.css" />
</head>
<body>
	<!-- 상단 헤더 -->
	<div class="msg-header">
		<c:set var="prevPage" value="${sessionScope.prevPage}" />
		<button id="back-button" onclick="goBack()">←</button>
		<h2 class="msg-title">채팅 목록</h2>
	</div>

	<!-- 채팅 목록 -->
	<div class="chat-list">
		<c:forEach var="chat" items="${chatRoomList}">

			<!-- ✅ 이 조건문으로 감싸줘야 null user_id 때문에 생기는 오류 방지 가능 -->
			<c:if test="${not empty chat.user_id}">
				<div class="chat-item"
					onclick="location.href='personal_chat?receiver_id=${chat.user_id}&receiver_nick=${chat.user_nick}'">
					<img class="chat-profile"
						src="resources/img/${empty chat.user_profile ? 'default_profile.png' : chat.user_profile}" />
					<div class="chat-content">
						<div class="chat-top">
							<span class="chat-nick">${chat.user_nick}</span>
							<div class="chat-meta">
								<c:if test="${chat.unread_count > 0}">
									<span class="unread-badge">${chat.unread_count}</span>
								</c:if>
								<span class="chat-time">${chat.sended_at}</span>
							</div>
						</div>
						<div class="chat-msg">${chat.last_msg}</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
</body>
<script>
	function goBack() {
		const fromPopup = sessionStorage.getItem("fromProfilePopup");
		if (fromPopup === "true") {
			sessionStorage.removeItem("fromProfilePopup");
			// 프로필 팝업을 다시 띄우기 위해 메인으로 가도록 설정
			window.location.href = "main?showPopup=true";
		} else {
			history.back(); // 일반적인 경우엔 그냥 뒤로가기
		}
	}
	window.addEventListener("load", function() {
		if (sessionStorage.getItem("chatJustRead") === "true") {
			sessionStorage.removeItem("chatJustRead");
			location.reload(); // 🔁 강제 새로고침
		}
	});
	window.addEventListener("pageshow", function() {
		if (sessionStorage.getItem("chatJustRead") === "true") {
			sessionStorage.removeItem("chatJustRead");
			location.reload(); // ✅ 강제로 새로고침
		}
	});
</script>
</html>
