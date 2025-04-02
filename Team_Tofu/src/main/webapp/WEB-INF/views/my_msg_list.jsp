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

	<div class="chat-list">
		<!-- 그룹채팅 -->
		<h3 class="section-title">그룹 채팅</h3>
		<c:forEach var="room" items="${groupList}">
			<div class="chat-item"
				onclick="location.href='group_chat?croom_idx=${room.croom_idx}'">
				<img class="chat-profile" src="resources/img/default_profile.png" />
				<div class="chat-content">
					<div class="chat-top">
						<span class="chat-nick">${room.croom_title}</span>
						<div class="chat-meta">
							<c:if
								test="${room.unread_count != null and room.unread_count > 0}">
								<span class="unread-badge">${room.unread_count}</span>
							</c:if>
							<span class="chat-time">${room.sended_at}</span>
						</div>
					</div>
					<div class="chat-msg">${room.last_msg}</div>
				</div>
			</div>
		</c:forEach>
		<!-- 개인 채팅 목록 -->
		<h3 class="section-title">개인 채팅</h3>
		<c:forEach var="chat" items="${personalList}">
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
		if (sessionStorage.getItem("groupChatJustRead") === "true") {
			sessionStorage.removeItem("groupChatJustRead");
			location.reload(); // 🔁 메세지함 자동 새로고침
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