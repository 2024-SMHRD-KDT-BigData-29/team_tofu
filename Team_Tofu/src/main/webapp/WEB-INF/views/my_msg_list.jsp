<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>채팅 목록</title>
<link rel="stylesheet" href="resources/css/my_msg_list.css" />
</head>
<body>
<div class="msg-header">
  <button id="back-button" onclick="history.back()">←</button>
  </div>
	<h2>채팅 목록</h2>
	<div class="chat-list">
		<c:forEach var="chat" items="${chatRoomList}">
			<div class="chat-item"
				onclick="location.href='personal_chat?receiver_id=${chat.user_id}&receiver_nick=${chat.user_nick}'">
				<img class="chat-profile"
					src="resources/img/${empty chat.user_profile ? 'default_profile.png' : chat.user_profile}" />
				<div class="chat-content">
					<div class="chat-top">
						<span class="chat-nick">${chat.user_nick}</span> <span
							class="chat-time">${chat.sended_at}</span>
					</div>
					<div class="chat-msg">${chat.last_msg}</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
