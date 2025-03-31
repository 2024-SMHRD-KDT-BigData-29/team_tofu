// 메세지 전송기능
document.getElementById('send-button').addEventListener('click', function() {
    sendMessage();
});

document.getElementById('message-input').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        sendMessage();
    }
});

document.getElementById('back-button').addEventListener('click', function() {
    alert('뒤로가기 버튼이 클릭되었습니다.'); // 실제로는 페이지 이동 또는 다른 동작을 구현
});

// 상대방의 응답 목록
const responses = [
    "네, 알겠습니다.",
    "그렇게 해주세요.",
    "좋은 생각이에요.",
    "잘 들었습니다.",
    "그런가요?",
    "좋아요!",
    "고마워요!",
    "그럼요!",
    "물론이죠.",
    "확인했습니다."
];

function getRandomResponse() {
    return responses[Math.floor(Math.random() * responses.length)];
}

function sendMessage() {
    const input = document.getElementById('message-input');
    const message = input.value.trim();
    if (message !== '') {
        const chatMessages = document.getElementById('chat-messages');
        const messageElement = document.createElement('div');
        messageElement.classList.add('message', 'user');
        messageElement.textContent = message;
        chatMessages.appendChild(messageElement);
        input.value = '';
        chatMessages.scrollTop = chatMessages.scrollHeight;

        // 상대방의 응답을 시뮬레이션
        setTimeout(() => {
            const responseElement = document.createElement('div');
            responseElement.classList.add('message', 'other');
            responseElement.textContent = getRandomResponse();
            chatMessages.appendChild(responseElement);
            chatMessages.scrollTop = chatMessages.scrollHeight;
        }, 1000);
    }
}