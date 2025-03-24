// 뒤로가기 버튼 기능
document.getElementById('back-button').addEventListener('click', function() {
    alert('뒤로가기 버튼이 클릭되었습니다.'); // 실제로는 페이지 이동 또는 다른 동작을 구현
});

// 참여하기 버튼 기능
document.querySelectorAll('.join-button').forEach(button => {
    button.addEventListener('click', function() {
        const roomTitle = this.parentElement.querySelector('h3').textContent;
        alert(roomTitle + "에 참여합니다!"); // 실제로는 참여 로직을 구현
    });
});