document.addEventListener('DOMContentLoaded', function() {
    const editButton = document.getElementById('edit-button');
    const postContent = document.getElementById('post-content');
    const saveButton = document.getElementById('save-post-button');

    // 편집 모드 토글
    editButton.addEventListener('click', function() {
        postContent.classList.toggle('editable');
        postContent.contentEditable = postContent.classList.contains('editable');
        editButton.classList.toggle('active');
        editButton.textContent = postContent.classList.contains('editable') ? '확인' : '편집';

        if (postContent.classList.contains('editable')) {
            postContent.focus();
        }
    });

    // 저장 버튼 클릭 시
    saveButton.addEventListener('click', function() {
        if (postContent.classList.contains('editable')) {
            alert('게시글이 저장되었습니다!\n저장된 내용: ' + postContent.textContent);

            // 기존 내용으로 새 p 태그 생성
            const newParagraph = document.createElement('p');
            newParagraph.textContent = postContent.textContent;
            newParagraph.className = "post-text"; // 스타일 적용을 위해 클래스 추가

            // 기존 postContent 교체
            postContent.replaceWith(newParagraph);

            // 변수 재할당
            postContent = newParagraph;

            editButton.classList.remove('active');
            editButton.textContent = '편집';
        }
    });

    // 이전 게시글 클릭 이벤트
    document.querySelectorAll('.post-item').forEach(item => {
        item.addEventListener('click', function() {
            const text = this.querySelector('.post-text').textContent;
            postContent.textContent = text;
        });
    });
});
