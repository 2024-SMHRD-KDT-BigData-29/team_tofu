// 파일 추가 버튼 클릭 시 새로운 파일 입력 필드 추가
document.getElementById('add-file-btn').addEventListener('click', function() {
    const fileUploadSection = document.getElementById('file-upload-section');

    // 새로운 파일 업로드 컨테이너 생성
    const newFileUpload = document.createElement('div');
    newFileUpload.className = 'file-upload';

    // 새로운 라벨 생성
    const newLabel = document.createElement('label');
    newLabel.className = 'file-label';
    newLabel.textContent = '추가 파일을 업로드하세요';
    newLabel.setAttribute('for', `file-${fileUploadSection.children.length}`);

    // 새로운 파일 입력 필드 생성
    const newFileInput = document.createElement('input');
    newFileInput.type = 'file';
    newFileInput.name = 'feed-file';
    newFileInput.className = 'file-input';
    newFileInput.id = `file-${fileUploadSection.children.length}`;

    // 파일 미리보기 컨테이너 생성
    const previewContainer = document.createElement('div');
    previewContainer.className = 'preview-container';

    // 파일 입력 필드 변경 시 미리보기 표시
    newFileInput.addEventListener('change', function(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                // 이미지 미리보기 생성
                const preview = document.createElement('img');
                preview.src = e.target.result;
                preview.style.maxWidth = '100%';
                preview.style.maxHeight = '150px';
                preview.style.marginTop = '10px';
                previewContainer.innerHTML = ''; // 기존 미리보기 초기화
                previewContainer.appendChild(preview);
            };
            reader.readAsDataURL(file);
        } else {
            previewContainer.innerHTML = ''; // 파일이 없으면 미리보기 초기화
        }
    });

    // 삭제 버튼 생성
    const deleteButton = document.createElement('button');
    deleteButton.className = 'delete-file-btn';
    deleteButton.addEventListener('click', function() {
        fileUploadSection.removeChild(newFileUpload);
    });

    // 라벨, 입력 필드, 삭제 버튼을 컨테이너에 추가
    newFileUpload.appendChild(newLabel);
    newFileUpload.appendChild(newFileInput);
    newFileUpload.appendChild(deleteButton);

    // 파일 업로드 섹션에 새로운 컨테이너 추가
    fileUploadSection.insertBefore(previewContainer, fileUploadSection.firstChild); // 미리보기를 맨 위에 추가
    fileUploadSection.appendChild(newFileUpload);
});