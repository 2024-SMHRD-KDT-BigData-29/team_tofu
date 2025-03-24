document.addEventListener("DOMContentLoaded", function () {
    // 이미지 미리보기 함수
    function previewImage(input, container) {
        const file = input.files[0];
        if (file && file.type.startsWith("image/")) {
            const reader = new FileReader();
            reader.onload = function (e) {
                container.innerHTML = `
                    <div style="position: relative; display: inline-block;">
                        <img src="${e.target.result}" alt="이미지 미리보기" class="image-preview">
                        <button class="delete-file-btn">&times;</button>
                    </div>
                `;

                // 삭제 버튼 이벤트 리스너 추가
                const deleteBtn = container.querySelector(".delete-file-btn");
                deleteBtn.addEventListener("click", function () {
                    input.value = ""; // 파일 입력 필드 초기화
                    container.innerHTML = ""; // 미리보기 컨테이너 초기화
                });
            };
            reader.readAsDataURL(file); // 파일을 Data URL로 읽기
        }
    }

    // 기존 파일 업로드 필드에 대한 미리보기 적용
    const existingFileInput = document.querySelector("input[name='feed-file']");
    const existingPreviewContainer = document.querySelector(".preview-container");
    if (existingFileInput && existingPreviewContainer) {
        existingFileInput.addEventListener("change", function () {
            previewImage(existingFileInput, existingPreviewContainer);
        });
    }
    // 파일 추가 버튼 클릭 시 새로운 파일 입력 필드 추가
    const addFileBtn = document.getElementById("add-file-btn");
	if (addFileBtn) {
    	addFileBtn.addEventListener("click", function () {
       		const fileUploadSection = document.getElementById("file-upload-section");

        // 새로운 파일 업로드 컨테이너 생성
        const newFileUpload = document.createElement('div');
        newFileUpload.className = 'file-upload';

        // 새로운 파일 입력 필드 생성
        const newFileInput = document.createElement('input');
        newFileInput.type = 'file';
        newFileInput.name = 'feed-file';
        newFileInput.className = 'file-input';
        newFileInput.style.display = 'none'; // 파일 입력 필드 숨기기

        // 새로운 라벨 생성
        const newLabel = document.createElement('label');
        newLabel.className = 'file-label';
        newLabel.textContent = '파일 선택';
        newLabel.addEventListener("click", function () {
            newFileInput.click(); // 라벨 클릭 시 파일 선택 창 열기
        });

        // 삭제 버튼 생성
        const deleteButton = document.createElement('button');
        deleteButton.className = 'delete-file-btn';
        deleteButton.addEventListener('click', function () {
            fileUploadSection.removeChild(newPreviewContainer); // 미리보기 컨테이너 삭제
            fileUploadSection.removeChild(newFileUpload); // 파일 업로드 컨테이너 삭제
        });

        // 파일 미리보기 컨테이너 생성
        const newPreviewContainer = document.createElement('div');
        newPreviewContainer.className = 'preview-container';

        // 파일 입력 필드 변경 시 미리보기 표시
        newFileInput.addEventListener('change', function () {
            previewImage(newFileInput, newPreviewContainer);
        });

        // 파일 업로드 섹션에 새로운 컨테이너 추가
        newFileUpload.appendChild(newLabel);
        newFileUpload.appendChild(newFileInput);
        newFileUpload.appendChild(deleteButton);
        fileUploadSection.appendChild(newFileUpload);
        fileUploadSection.appendChild(newPreviewContainer);
		});
	}
});