<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>그룹 채팅</title>
<link rel="stylesheet" href="resources/css/group_chat.css">
</head>
<body>
	<div class="chat-container">
		<div class="chat-header">
			<button id="back-button" onclick="history.back()">←</button>
			<h2 id="chat-title">그룹 채팅방</h2>
		</div>
		<div class="chat-messages" id="chat-messages">
			<!-- 메시지가 여기에 추가됨 -->
		</div>
		<div class="chat-input">
			<input type="text" id="message-input" placeholder="메시지를 입력하세요...">
			<button id="send-button">전송</button>
		</div>
	</div>

	<script>
		const croom_idx = "${param.croom_idx}";
		const chatter = "${login_user.user_nick}";
	</script>
	<script src="resources/js/group_chat.js"></script>

</body>
</html>
