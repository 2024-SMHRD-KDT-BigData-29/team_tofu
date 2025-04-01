const senderId = sessionStorage.getItem("sender_id");
const receiverId = sessionStorage.getItem("receiver_id");

if (!senderId || !receiverId) {
	alert("세션에 sender_id 또는 receiver_id가 없습니다.");
	throw new Error("세션에 sender_id 또는 receiver_id가 없습니다.");
}

// ✅ WebSocket 선언
const socket = new WebSocket("ws://" + location.host + "/furture/personal");

// ✅ 연결 성공
socket.onopen = function() {
	console.log("✅ 웹소켓 연결 완료");
};

// ✅ 메시지 수신 처리
socket.onmessage = function(event) {
	const msg = JSON.parse(event.data);

	// 내가 보낸 메시지는 이미 JSP에서 출력되었으므로 중복 표시하지 않음
	if (msg.sender_id === senderId) {
		console.log("✋ 내가 방금 보낸 메시지, 중복 출력 생략");
		return;
	}

	console.log("📩 메시지 수신:", msg);

	const alignment = "other";
	const messageElement = document.createElement("div");
	messageElement.className = `message ${alignment}`;
	messageElement.innerHTML = `<b>${msg.sender_id}</b>: ${msg.p_msg_content}`;
	document.getElementById("chat-messages").appendChild(messageElement);

	// 스크롤 아래로 자동 이동
	const chatBox = document.getElementById("chat-messages");
	chatBox.scrollTop = chatBox.scrollHeight;
};

// ✅ 에러 발생 시
socket.onerror = function(error) {
	console.error("❌ 웹소켓 에러:", error);
};

// ✅ 연결 종료 시
socket.onclose = function() {
	console.warn("⚠️ 웹소켓 연결 종료");
};

// ✅ 메시지 전송 버튼 클릭 시
document.getElementById("send-button").addEventListener("click", function() {
	const content = document.getElementById("message-input").value;
	if (!content) return;

	const msg = {
		sender_id: senderId,
		receiver_id: receiverId,
		p_msg_content: content
	};

	if (socket.readyState === WebSocket.OPEN) {
		socket.send(JSON.stringify(msg));
		document.getElementById("message-input").value = "";

		// ✅ 내가 보낸 메시지 화면에 직접 append!
		const messageElement = document.createElement("div");
		messageElement.className = `message user`;
		messageElement.innerHTML = `<b>${senderId}</b>: ${msg.p_msg_content}`;
		document.getElementById("chat-messages").appendChild(messageElement);

		const chatBox = document.getElementById("chat-messages");
		chatBox.scrollTop = chatBox.scrollHeight;
	} else {
		alert("⚠️ 채팅 서버에 연결되어 있지 않습니다.");
		console.warn("소켓 상태:", socket.readyState);
	}
});

document.addEventListener('DOMContentLoaded', function() {
	fetch('/unread_count') // 개인 채팅 안 읽은 메시지 수
		.then(res => res.json())
		.then(count => {
			if (count > 0) {
				const badge = document.getElementById('notification-count');
				badge.innerText = count;
				badge.style.display = 'inline-block';
			}
		});
});