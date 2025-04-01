// group_chat.js
const croom_idx = window.croom_idx || 1;
const chatter = window.chatter || "익명";

// 메시지 로딩
function loadMessages() {
    $.ajax({
        url: "/chat/messages/" + croom_idx,
        method: "GET",
        success: function (messages) {
            $("#chat-messages").html(""); // 기존 비우기
            messages.forEach(msg => {
                const alignment = msg.chatter === chatter ? "user" : "other";
                $("#chat-messages").append(
                    `<div class="message ${alignment}"><b>${msg.chatter}</b>: ${msg.chat_content}</div>`
                );
            });
        },
        error: function (err) {
            console.error("메시지 불러오기 실패:", err);
        }
    });
}

// 메시지 전송
$("#send-button").click(function () {
    const message = $("#message-input").val();
    if (!message) return;

    $.ajax({
        url: "/chat/send",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            croom_idx: croom_idx,
            chatter: chatter,
            chat_content: message
        }),
        success: function () {
            $("#message-input").val("");
            loadMessages();
        },
        error: function (err) {
            console.error("메시지 전송 실패:", err);
        }
    });
});

$(document).ready(function () {
    loadMessages();
    setInterval(loadMessages, 2000);
});
