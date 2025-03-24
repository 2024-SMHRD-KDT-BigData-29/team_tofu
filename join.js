// 비밀번호와 비밀번호 확인 입력값 실시간 비교
document.getElementById('confirm_pw').addEventListener('input', function() {
    const password = document.getElementById('user_pw').value;
    const confirmPassword = this.value;
    const messageElement = document.getElementById('password-match-message');

    if (password === confirmPassword) {
        messageElement.textContent = '비밀번호가 일치합니다.';
        messageElement.className = 'valid'; // 일치할 때 클래스 추가
    } else {
        messageElement.textContent = '비밀번호가 일치하지 않습니다.';
        messageElement.className = 'invalid'; // 불일치할 때 클래스 추가
    }
});

// 폼 제출 시 최종 검증
document.getElementById('join-form').addEventListener('submit', function(event) {
    const password = document.getElementById('user_pw').value;
    const confirmPassword = document.getElementById('confirm_pw').value;

    if (password !== confirmPassword) {
        alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.');
        event.preventDefault(); // 폼 제출 방지
    } else {
        alert('회원가입이 완료되었습니다!');
        // 여기서 추가적인 유효성 검사 또는 서버로 데이터 전송 로직을 구현할 수 있습니다.
    }
});
