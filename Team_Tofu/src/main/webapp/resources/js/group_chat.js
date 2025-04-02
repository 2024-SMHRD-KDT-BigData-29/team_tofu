const croom_idx = document.getElementById("group-chat-box").getAttribute("data-croom-idx");
const chatter = document.getElementById("group-chat-box").getAttribute("data-chatter");
console.log("✅ croom_idx:", croom_idx);
// ✅ WebSocket 연결
const socket = new WebSocket("ws://" + location.host + "/furture/group");

socket.onopen = () => {
	console.log("✅ 그룹채팅 웹소켓 연결 완료");
};

socket.onmessage = (event) => {
	const msg = JSON.parse(event.data);
	if (parseInt(msg.croom_idx) === parseInt(croom_idx)) {
		appendMessage(msg);
		scrollToBottom(); // 새 메시지일 때 스크롤 아래로
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

// ✅ 메시지 추가 함수
function appendMessage(msg) {
	const box = document.getElementById("group-chat-messages");

	const msgDiv = document.createElement("div");
	msgDiv.className = (msg.chatter === chatter) ? "message user" : "message other";
	msgDiv.innerHTML = `
		<span class="nickname">${msg.chatter}</span>
		<div class="bubble">${msg.chat_content}</div>
	`;
	box.appendChild(msgDiv);

	setTimeout(() => {
		box.scrollTop = box.scrollHeight;
	}, 0);
}

// ✅ 스크롤 맨 아래로 이동
function scrollToBottom() {
	const chatBox = document.getElementById("group-chat-messages");
		chatBox.scrollTop = chatBox.scrollHeight;
	// ✅ DOM 반영 이후로 delay 줌
}

// ✅ 기존 메시지 불러오기 (DOM 완전히 로드된 후)
window.addEventListener("DOMContentLoaded", () => {
	fetch("chat/messages?croom_idx=" + croom_idx)
		.then(res => {
			if (!res.ok) throw new Error("서버 응답 실패");
			return res.json();
		})
		.then(list => {
			list.forEach(msg => appendMessage(msg));
			scrollToBottom(); // 기존 메시지도 끝으로 이동
		})
		.catch(err => {
			console.error("❌ 기존 메시지 불러오기 실패:", err);
		});
});