const croom_idx = document.getElementById("group-chat-box").getAttribute("data-croom-idx");
const chatter = document.getElementById("group-chat-box").getAttribute("data-chatter");

// ✅ WebSocket 연결
const socket = new WebSocket("ws://" + location.host + "/furture/group");

socket.onopen = () => {
	console.log("✅ 그룹채팅 웹소켓 연결 완료");
};

socket.onmessage = (event) => {
	const msg = JSON.parse(event.data);
	if (parseInt(msg.croom_idx) === parseInt(croom_idx)) {
		appendMessage(msg);
	}
};

socket.onerror = (error) => {
	console.error("❌ 웹소켓 에러:", error);
};

socket.onclose = () => {
	console.warn("⚠️ 웹소켓 연결 종료");
};

// ✅ 메시지 전송
const input = document.getElementById("group-message-input");
const button = document.getElementById("group-send-button");

button.addEventListener("click", sendMessage);
input.addEventListener("keypress", (e) => {
	if (e.key === "Enter") sendMessage();
});

function sendMessage() {
	const content = input.value.trim();
	if (!content) return;

	const msg = {
		croom_idx: croom_idx,
		chatter: chatter,
		chat_content: content
	};

	if (socket.readyState === WebSocket.OPEN) {
		socket.send(JSON.stringify(msg));
		input.value = "";
	} else {
		alert("⚠️ 연결이 끊어졌습니다.");
	}
}

// ✅ 메시지 추가
function appendMessage(msg) {
	const box = document.getElementById("group-chat-messages");
	const msgDiv = document.createElement("div");
	const isMine = msg.chatter === chatter;

	msgDiv.className = "message " + (isMine ? "user" : "other");
	msgDiv.innerHTML = `
		<span class="nickname">${msg.chatter}</span>
		<div class="bubble">${msg.chat_content}</div>
	`;
	box.appendChild(msgDiv);
	box.scrollTop = box.scrollHeight;
}


// ✅ 기존 메시지 불러오기 (onload 시점)
window.addEventListener("DOMContentLoaded", () => {
	fetch("chat/messages?croom_idx=" + croom_idx)
		.then(res => {
			if (!res.ok) throw new Error("서버 응답 실패");
			return res.json();
		})
		.then(list => {
			list.forEach(msg => appendMessage(msg));
		})
		.catch(err => {
			console.error("❌ 기존 메시지 불러오기 실패:", err);
		});
});
