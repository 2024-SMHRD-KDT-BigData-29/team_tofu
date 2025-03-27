document.addEventListener('DOMContentLoaded', function() {
    // DOM 요소 선택 (기존 아이디/클래스 유지)
    const form = document.getElementById('join-form');
    const fileInput = document.getElementById('user_profile');
    const previewContainer = document.getElementById('preview-container');
    const previewImage = document.getElementById('preview-image');
    const fileInfo = document.getElementById('file-info');
    const fileLabel = document.getElementById('file-label');
    const passwordInput = document.getElementById('user_pw');
    const confirmPasswordInput = document.getElementById('confirm_pw');
    const passwordMatchMessage = document.getElementById('password-match-message');

    // 1. 프로필 이미지 미리보기 기능
    fileInput.addEventListener('change', function(e) {
        const file = e.target.files[0];
        
        if (file) {
            // 파일 정보 표시
            fileInfo.textContent = `${file.name} (${formatFileSize(file.size)})`;
            
            // 이미지 파일인지 확인
            if (file.type.startsWith('image/')) {
                const reader = new FileReader();
                
                reader.onload = function(e) {
                    previewImage.src = e.target.result;
                    previewContainer.style.display = 'block';
                    fileLabel.innerHTML = '<i class="fas fa-sync-alt"></i> 프로필 사진 변경';
                };
                
                reader.onerror = function() {
                    alert('이미지 로딩에 실패했습니다.');
                    resetFileInput();
                };
                
                reader.readAsDataURL(file);
            } else {
                alert('이미지 파일만 선택할 수 있습니다. (JPEG, PNG 등)');
                resetFileInput();
            }
        } else {
            resetFileInput();
        }
    });

    // 2. 비밀번호 확인 기능
    confirmPasswordInput.addEventListener('input', checkPasswordMatch);
    passwordInput.addEventListener('input', checkPasswordMatch);

    // 3. 폼 제출 검증
    form.addEventListener('submit', function(e) {
        if (passwordInput.value !== confirmPasswordInput.value) {
            e.preventDefault();
            alert('비밀번호가 일치하지 않습니다.');
            confirmPasswordInput.focus();
        }
    });

    // Helper 함수들
    function resetFileInput() {
        fileInput.value = '';
        previewContainer.style.display = 'none';
        fileInfo.textContent = '';
        fileLabel.innerHTML = '<i class="fas fa-camera"></i> 프로필 사진 업로드';
        previewImage.src = 'resources/images/default-profile.png';
    }

    function checkPasswordMatch() {
        if (passwordInput.value && confirmPasswordInput.value) {
            if (passwordInput.value === confirmPasswordInput.value) {
                passwordMatchMessage.textContent = '비밀번호가 일치합니다.';
                passwordMatchMessage.className = 'message valid';
            } else {
                passwordMatchMessage.textContent = '비밀번호가 일치하지 않습니다.';
                passwordMatchMessage.className = 'message invalid';
            }
        } else {
            passwordMatchMessage.textContent = '';
            passwordMatchMessage.className = 'message';
        }
    }

    function formatFileSize(bytes) {
        if (bytes === 0) return '0 Bytes';
        const k = 1024;
        const sizes = ['Bytes', 'KB', 'MB'];
        const i = Math.floor(Math.log(bytes) / Math.log(k));
        return parseFloat((bytes / Math.pow(k, i)).toFixed(1)) + ' ' + sizes[i];
    }
});