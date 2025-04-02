window.addEventListener("load", function() {
	const senderId = sessionStorage.getItem("sender_id");
	const receiverId = sessionStorage.getItem("receiver_id");

	if (!senderId || !receiverId) {
		alert("세션에 sender_id 또는 receiver_id가 없습니다.");
		throw new Error("세션에 sender_id 또는 receiver_id가 없습니다.");
	}

	const socket = new WebSocket("ws://" + location.host + "/furture/personal");

	socket.onopen = function() {
		console.log("✅ 웹소켓 연결 완료");
	};

	socket.onmessage = function(event) {
		const msg = JSON.parse(event.data);

		if (msg.sender_id === senderId) {
			// 내가 보낸 메시지는 JSP에서 이미 출력됨
			return;
		}

		const messageElement = document.createElement("div");
		messageElement.className = `message other`;
		messageElement.innerHTML = `<b>${msg.sender_id}</b>: ${msg.p_msg_content}`;
		document.getElementById("chat-messages").appendChild(messageElement);
		scrollToBottom();
	};

	socket.onerror = function(error) {
		console.error("❌ 웹소켓 에러:", error);
	};

	socket.onclose = function() {
		console.warn("⚠️ 웹소켓 연결 종료");
	};

	// 전송 함수
	function sendMessage() {
		const input = document.getElementById("message-input");
		const content = input.value.trim();
		if (!content) return;

		const msg = {
			sender_id: senderId,
			receiver_id: receiverId,
			p_msg_content: content
		};

		if (socket.readyState === WebSocket.OPEN) {
			socket.send(JSON.stringify(msg));
			input.value = "";

			const messageElement = document.createElement("div");
			messageElement.className = `message user`;
			messageElement.innerHTML = `<b>${senderId}</b>: ${msg.p_msg_content}`;
			document.getElementById("chat-messages").appendChild(messageElement);
			scrollToBottom();
		} else {
			alert("⚠️ 채팅 서버에 연결되어 있지 않습니다.");
		}
	}

	// 전송 버튼 클릭
	document.getElementById("send-button").addEventListener("click", sendMessage);

	// ✅ Enter 키 입력 처리
	document.getElementById("message-input").addEventListener("keydown", function(e) {
		if (e.key === "Enter" && !e.shiftKey) {
			e.preventDefault();
			sendMessage();
		}
	});

	// 채팅창 아래로 이동
	function scrollToBottom() {
		const chatBox = document.getElementById("chat-messages");
		chatBox.scrollTop = chatBox.scrollHeight;
	}
});
